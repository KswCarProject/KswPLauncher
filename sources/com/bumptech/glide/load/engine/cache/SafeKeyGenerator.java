package com.bumptech.glide.load.engine.cache;

import android.support.v4.util.Pools;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SafeKeyGenerator {
    private final Pools.Pool<PoolableDigestContainer> digestPool = FactoryPools.threadSafe(10, new FactoryPools.Factory<PoolableDigestContainer>() {
        public PoolableDigestContainer create() {
            try {
                return new PoolableDigestContainer(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    });
    private final LruCache<Key, String> loadIdToSafeHash = new LruCache<>(1000);

    public String getSafeKey(Key key) {
        String safeKey;
        synchronized (this.loadIdToSafeHash) {
            safeKey = this.loadIdToSafeHash.get(key);
        }
        if (safeKey == null) {
            safeKey = calculateHexStringDigest(key);
        }
        synchronized (this.loadIdToSafeHash) {
            this.loadIdToSafeHash.put(key, safeKey);
        }
        return safeKey;
    }

    private String calculateHexStringDigest(Key key) {
        PoolableDigestContainer container = (PoolableDigestContainer) Preconditions.checkNotNull(this.digestPool.acquire());
        try {
            key.updateDiskCacheKey(container.messageDigest);
            return Util.sha256BytesToHex(container.messageDigest.digest());
        } finally {
            this.digestPool.release(container);
        }
    }

    private static final class PoolableDigestContainer implements FactoryPools.Poolable {
        final MessageDigest messageDigest;
        private final StateVerifier stateVerifier = StateVerifier.newInstance();

        PoolableDigestContainer(MessageDigest messageDigest2) {
            this.messageDigest = messageDigest2;
        }

        public StateVerifier getVerifier() {
            return this.stateVerifier;
        }
    }
}
