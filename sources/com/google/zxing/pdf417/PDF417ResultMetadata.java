package com.google.zxing.pdf417;

public final class PDF417ResultMetadata {
    private String addressee;
    private int checksum = -1;
    private String fileId;
    private String fileName;
    private long fileSize = -1;
    private boolean lastSegment;
    private int[] optionalData;
    private int segmentCount = -1;
    private int segmentIndex;
    private String sender;
    private long timestamp = -1;

    public int getSegmentIndex() {
        return this.segmentIndex;
    }

    public void setSegmentIndex(int segmentIndex2) {
        this.segmentIndex = segmentIndex2;
    }

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String fileId2) {
        this.fileId = fileId2;
    }

    @Deprecated
    public int[] getOptionalData() {
        return this.optionalData;
    }

    @Deprecated
    public void setOptionalData(int[] optionalData2) {
        this.optionalData = optionalData2;
    }

    public boolean isLastSegment() {
        return this.lastSegment;
    }

    public void setLastSegment(boolean lastSegment2) {
        this.lastSegment = lastSegment2;
    }

    public int getSegmentCount() {
        return this.segmentCount;
    }

    public void setSegmentCount(int segmentCount2) {
        this.segmentCount = segmentCount2;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender2) {
        this.sender = sender2;
    }

    public String getAddressee() {
        return this.addressee;
    }

    public void setAddressee(String addressee2) {
        this.addressee = addressee2;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName2) {
        this.fileName = fileName2;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long fileSize2) {
        this.fileSize = fileSize2;
    }

    public int getChecksum() {
        return this.checksum;
    }

    public void setChecksum(int checksum2) {
        this.checksum = checksum2;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp2) {
        this.timestamp = timestamp2;
    }
}
