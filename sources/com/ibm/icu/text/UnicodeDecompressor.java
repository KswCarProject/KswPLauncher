package com.ibm.icu.text;

import com.wits.ksw.settings.BrightnessUtils;
import com.wits.pms.statuscontrol.WitsCommand;
import kotlin.UByte;

/* loaded from: classes.dex */
public final class UnicodeDecompressor implements SCSU {
    private static final int BUFSIZE = 3;
    private int fCurrentWindow = 0;
    private int[] fOffsets = new int[8];
    private int fMode = 0;
    private byte[] fBuffer = new byte[3];
    private int fBufferLength = 0;

    public UnicodeDecompressor() {
        reset();
    }

    public static String decompress(byte[] buffer) {
        char[] buf = decompress(buffer, 0, buffer.length);
        return new String(buf);
    }

    public static char[] decompress(byte[] buffer, int start, int limit) {
        UnicodeDecompressor comp = new UnicodeDecompressor();
        int len = Math.max(2, (limit - start) * 2);
        char[] temp = new char[len];
        int charCount = comp.decompress(buffer, start, limit, null, temp, 0, len);
        char[] result = new char[charCount];
        System.arraycopy(temp, 0, result, 0, charCount);
        return result;
    }

    public int decompress(byte[] byteBuffer, int byteBufferStart, int byteBufferLimit, int[] bytesRead, char[] charBuffer, int charBufferStart, int charBufferLimit) {
        int i;
        int newBytes;
        int newBytes2;
        int bytePos = byteBufferStart;
        int ucPos = charBufferStart;
        if (charBuffer.length < 2 || charBufferLimit - charBufferStart < 2) {
            throw new IllegalArgumentException("charBuffer.length < 2");
        }
        int i2 = this.fBufferLength;
        if (i2 <= 0) {
            i = 0;
        } else {
            if (i2 == 3) {
                newBytes = 0;
            } else {
                byte[] bArr = this.fBuffer;
                int newBytes3 = bArr.length - i2;
                int newBytes4 = byteBufferLimit - byteBufferStart;
                if (newBytes4 >= newBytes3) {
                    newBytes2 = newBytes3;
                } else {
                    newBytes2 = byteBufferLimit - byteBufferStart;
                }
                System.arraycopy(byteBuffer, byteBufferStart, bArr, i2, newBytes2);
                newBytes = newBytes2;
            }
            this.fBufferLength = 0;
            byte[] bArr2 = this.fBuffer;
            i = 0;
            int count = decompress(bArr2, 0, bArr2.length, null, charBuffer, charBufferStart, charBufferLimit);
            ucPos += count;
            bytePos += newBytes;
        }
        while (true) {
            if (bytePos < byteBufferLimit && ucPos < charBufferLimit) {
                switch (this.fMode) {
                    case 0:
                        while (true) {
                            if (bytePos < byteBufferLimit && ucPos < charBufferLimit) {
                                int bytePos2 = bytePos + 1;
                                int aByte = byteBuffer[bytePos] & UByte.MAX_VALUE;
                                switch (aByte) {
                                    case 0:
                                    case 9:
                                    case 10:
                                    case 13:
                                    case 32:
                                    case 33:
                                    case 34:
                                    case 35:
                                    case 36:
                                    case 37:
                                    case 38:
                                    case 39:
                                    case 40:
                                    case 41:
                                    case 42:
                                    case 43:
                                    case 44:
                                    case 45:
                                    case 46:
                                    case 47:
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                    case 58:
                                    case 59:
                                    case 60:
                                    case 61:
                                    case 62:
                                    case 63:
                                    case 64:
                                    case 65:
                                    case 66:
                                    case 67:
                                    case 68:
                                    case 69:
                                    case 70:
                                    case 71:
                                    case 72:
                                    case 73:
                                    case 74:
                                    case 75:
                                    case 76:
                                    case 77:
                                    case 78:
                                    case 79:
                                    case 80:
                                    case 81:
                                    case 82:
                                    case 83:
                                    case 84:
                                    case 85:
                                    case 86:
                                    case 87:
                                    case 88:
                                    case 89:
                                    case 90:
                                    case 91:
                                    case 92:
                                    case 93:
                                    case 94:
                                    case 95:
                                    case 96:
                                    case 97:
                                    case 98:
                                    case 99:
                                    case 100:
                                    case 101:
                                    case 102:
                                    case 103:
                                    case 104:
                                    case 105:
                                    case 106:
                                    case 107:
                                    case 108:
                                    case 109:
                                    case 110:
                                    case 111:
                                    case 112:
                                    case 113:
                                    case 114:
                                    case 115:
                                    case 116:
                                    case 117:
                                    case 118:
                                    case 119:
                                    case 120:
                                    case 121:
                                    case 122:
                                    case 123:
                                    case 124:
                                    case 125:
                                    case 126:
                                    case 127:
                                        charBuffer[ucPos] = (char) aByte;
                                        bytePos = bytePos2;
                                        ucPos++;
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                        if (bytePos2 >= byteBufferLimit) {
                                            int bytePos3 = bytePos2 - 1;
                                            System.arraycopy(byteBuffer, bytePos3, this.fBuffer, i, byteBufferLimit - bytePos3);
                                            int i3 = byteBufferLimit - bytePos3;
                                            this.fBufferLength = i3;
                                            bytePos = bytePos3 + i3;
                                            break;
                                        } else {
                                            int bytePos4 = bytePos2 + 1;
                                            int dByte = byteBuffer[bytePos2] & 255;
                                            int ucPos2 = ucPos + 1;
                                            charBuffer[ucPos] = (char) (((dByte < 0 || dByte >= 128) ? this.fOffsets[aByte - 1] - 128 : sOffsets[aByte - 1]) + dByte);
                                            bytePos = bytePos4;
                                            ucPos = ucPos2;
                                        }
                                    case 11:
                                        if (bytePos2 + 1 >= byteBufferLimit) {
                                            int bytePos5 = bytePos2 - 1;
                                            System.arraycopy(byteBuffer, bytePos5, this.fBuffer, i, byteBufferLimit - bytePos5);
                                            int i4 = byteBufferLimit - bytePos5;
                                            this.fBufferLength = i4;
                                            bytePos = bytePos5 + i4;
                                            break;
                                        } else {
                                            int bytePos6 = bytePos2 + 1;
                                            int aByte2 = byteBuffer[bytePos2] & 255;
                                            int i5 = (aByte2 & 224) >> 5;
                                            this.fCurrentWindow = i5;
                                            this.fOffsets[i5] = (((byteBuffer[bytePos6] & 255) | ((aByte2 & 31) << 8)) * 128) + 65536;
                                            bytePos = bytePos6 + 1;
                                        }
                                    case 12:
                                    default:
                                        bytePos = bytePos2;
                                    case 14:
                                        if (bytePos2 + 1 >= byteBufferLimit) {
                                            int bytePos7 = bytePos2 - 1;
                                            System.arraycopy(byteBuffer, bytePos7, this.fBuffer, i, byteBufferLimit - bytePos7);
                                            int i6 = byteBufferLimit - bytePos7;
                                            this.fBufferLength = i6;
                                            bytePos = bytePos7 + i6;
                                            break;
                                        } else {
                                            int bytePos8 = bytePos2 + 1;
                                            charBuffer[ucPos] = (char) ((byteBuffer[bytePos8] & 255) | (byteBuffer[bytePos2] << 8));
                                            ucPos++;
                                            bytePos = bytePos8 + 1;
                                        }
                                    case 15:
                                        this.fMode = 1;
                                        bytePos = bytePos2;
                                        break;
                                    case 16:
                                    case 17:
                                    case 18:
                                    case 19:
                                    case 20:
                                    case 21:
                                    case 22:
                                    case 23:
                                        this.fCurrentWindow = aByte - 16;
                                        bytePos = bytePos2;
                                    case 24:
                                    case 25:
                                    case 26:
                                    case 27:
                                    case 28:
                                    case 29:
                                    case 30:
                                    case 31:
                                        if (bytePos2 >= byteBufferLimit) {
                                            int bytePos9 = bytePos2 - 1;
                                            System.arraycopy(byteBuffer, bytePos9, this.fBuffer, i, byteBufferLimit - bytePos9);
                                            int i7 = byteBufferLimit - bytePos9;
                                            this.fBufferLength = i7;
                                            bytePos = bytePos9 + i7;
                                            break;
                                        } else {
                                            int i8 = aByte - 24;
                                            this.fCurrentWindow = i8;
                                            this.fOffsets[i8] = sOffsetTable[byteBuffer[bytePos2] & 255];
                                            bytePos = bytePos2 + 1;
                                        }
                                    case 128:
                                    case 129:
                                    case 130:
                                    case 131:
                                    case WitsCommand.SystemCommand.PRE_SEEK_FM /* 132 */:
                                    case WitsCommand.SystemCommand.NEXT_DOT_SEEK_FM /* 133 */:
                                    case WitsCommand.SystemCommand.PRE_DOT_SEEK_FM /* 134 */:
                                    case WitsCommand.SystemCommand.ST_SWITCH /* 135 */:
                                    case WitsCommand.SystemCommand.LOC_SWITCH /* 136 */:
                                    case 137:
                                    case 138:
                                    case 139:
                                    case 140:
                                    case 141:
                                    case 142:
                                    case 143:
                                    case 144:
                                    case 145:
                                    case 146:
                                    case 147:
                                    case 148:
                                    case 149:
                                    case 150:
                                    case 151:
                                    case 152:
                                    case 153:
                                    case 154:
                                    case 155:
                                    case 156:
                                    case 157:
                                    case 158:
                                    case 159:
                                    case 160:
                                    case 161:
                                    case 162:
                                    case 163:
                                    case 164:
                                    case 165:
                                    case 166:
                                    case 167:
                                    case 168:
                                    case 169:
                                    case 170:
                                    case 171:
                                    case 172:
                                    case 173:
                                    case 174:
                                    case 175:
                                    case 176:
                                    case 177:
                                    case 178:
                                    case 179:
                                    case 180:
                                    case 181:
                                    case 182:
                                    case 183:
                                    case 184:
                                    case 185:
                                    case 186:
                                    case 187:
                                    case 188:
                                    case 189:
                                    case 190:
                                    case 191:
                                    case 192:
                                    case 193:
                                    case 194:
                                    case 195:
                                    case 196:
                                    case 197:
                                    case 198:
                                    case 199:
                                    case 200:
                                    case WitsCommand.SystemCommand.UPDATE_SYSTEM /* 201 */:
                                    case WitsCommand.SystemCommand.UPDATE_MCU /* 202 */:
                                    case WitsCommand.SystemCommand.UPDATE_POWER_VOLTAGE /* 203 */:
                                    case WitsCommand.SystemCommand.LOW_POWER_VOLTAGE_WARNING /* 204 */:
                                    case WitsCommand.SystemCommand.DISMISS_POWER_VOLTAGE_DIALOG /* 205 */:
                                    case WitsCommand.SystemCommand.LOW_OR_HIGH_POWER_VOLTAGE_SHUTDOWN /* 206 */:
                                    case WitsCommand.SystemCommand.REBOOT_MACHINE /* 207 */:
                                    case WitsCommand.SystemCommand.SET_PWM_DUTY_RATIO /* 208 */:
                                    case WitsCommand.SystemCommand.SAVE_TP_ALIGN_DATA /* 209 */:
                                    case WitsCommand.SystemCommand.UPDATE_FK_CONFIG /* 210 */:
                                    case 211:
                                    case 212:
                                    case 213:
                                    case 214:
                                    case 215:
                                    case 216:
                                    case 217:
                                    case 218:
                                    case 219:
                                    case WitsCommand.SystemCommand.IMPORT_CONFIG /* 220 */:
                                    case 221:
                                    case 222:
                                    case 223:
                                    case 224:
                                    case SCSU.UCHANGE1 /* 225 */:
                                    case SCSU.UCHANGE2 /* 226 */:
                                    case SCSU.UCHANGE3 /* 227 */:
                                    case SCSU.UCHANGE4 /* 228 */:
                                    case SCSU.UCHANGE5 /* 229 */:
                                    case SCSU.UCHANGE6 /* 230 */:
                                    case SCSU.UCHANGE7 /* 231 */:
                                    case SCSU.UDEFINE0 /* 232 */:
                                    case SCSU.UDEFINE1 /* 233 */:
                                    case SCSU.UDEFINE2 /* 234 */:
                                    case SCSU.UDEFINE3 /* 235 */:
                                    case SCSU.UDEFINE4 /* 236 */:
                                    case SCSU.UDEFINE5 /* 237 */:
                                    case SCSU.UDEFINE6 /* 238 */:
                                    case SCSU.UDEFINE7 /* 239 */:
                                    case SCSU.UQUOTEU /* 240 */:
                                    case SCSU.UDEFINEX /* 241 */:
                                    case SCSU.URESERVED /* 242 */:
                                    case 243:
                                    case 244:
                                    case 245:
                                    case 246:
                                    case 247:
                                    case 248:
                                    case SCSU.LATININDEX /* 249 */:
                                    case 250:
                                    case SCSU.GREEKINDEX /* 251 */:
                                    case SCSU.ARMENIANINDEX /* 252 */:
                                    case SCSU.HIRAGANAINDEX /* 253 */:
                                    case SCSU.KATAKANAINDEX /* 254 */:
                                    case 255:
                                        int[] iArr = this.fOffsets;
                                        int i9 = this.fCurrentWindow;
                                        if (iArr[i9] <= 65535) {
                                            charBuffer[ucPos] = (char) ((iArr[i9] + aByte) - 128);
                                            bytePos = bytePos2;
                                            ucPos++;
                                        } else if (ucPos + 1 >= charBufferLimit) {
                                            int bytePos10 = bytePos2 - 1;
                                            System.arraycopy(byteBuffer, bytePos10, this.fBuffer, i, byteBufferLimit - bytePos10);
                                            int i10 = byteBufferLimit - bytePos10;
                                            this.fBufferLength = i10;
                                            bytePos = bytePos10 + i10;
                                            break;
                                        } else {
                                            int normalizedBase = iArr[i9] - 65536;
                                            int ucPos3 = ucPos + 1;
                                            charBuffer[ucPos] = (char) ((normalizedBase >> 10) + 55296);
                                            charBuffer[ucPos3] = (char) ((normalizedBase & BrightnessUtils.GAMMA_SPACE_MAX) + UTF16.TRAIL_SURROGATE_MIN_VALUE + (aByte & 127));
                                            bytePos = bytePos2;
                                            ucPos = ucPos3 + 1;
                                        }
                                }
                            }
                        }
                        break;
                    case 1:
                        while (true) {
                            if (bytePos < byteBufferLimit && ucPos < charBufferLimit) {
                                int bytePos11 = bytePos + 1;
                                int aByte3 = byteBuffer[bytePos] & UByte.MAX_VALUE;
                                switch (aByte3) {
                                    case 224:
                                    case SCSU.UCHANGE1 /* 225 */:
                                    case SCSU.UCHANGE2 /* 226 */:
                                    case SCSU.UCHANGE3 /* 227 */:
                                    case SCSU.UCHANGE4 /* 228 */:
                                    case SCSU.UCHANGE5 /* 229 */:
                                    case SCSU.UCHANGE6 /* 230 */:
                                    case SCSU.UCHANGE7 /* 231 */:
                                        this.fCurrentWindow = aByte3 - 224;
                                        this.fMode = i;
                                        bytePos = bytePos11;
                                        break;
                                    case SCSU.UDEFINE0 /* 232 */:
                                    case SCSU.UDEFINE1 /* 233 */:
                                    case SCSU.UDEFINE2 /* 234 */:
                                    case SCSU.UDEFINE3 /* 235 */:
                                    case SCSU.UDEFINE4 /* 236 */:
                                    case SCSU.UDEFINE5 /* 237 */:
                                    case SCSU.UDEFINE6 /* 238 */:
                                    case SCSU.UDEFINE7 /* 239 */:
                                        if (bytePos11 >= byteBufferLimit) {
                                            int bytePos12 = bytePos11 - 1;
                                            System.arraycopy(byteBuffer, bytePos12, this.fBuffer, i, byteBufferLimit - bytePos12);
                                            int i11 = byteBufferLimit - bytePos12;
                                            this.fBufferLength = i11;
                                            bytePos = bytePos12 + i11;
                                            break;
                                        } else {
                                            int i12 = aByte3 - 232;
                                            this.fCurrentWindow = i12;
                                            this.fOffsets[i12] = sOffsetTable[byteBuffer[bytePos11] & 255];
                                            this.fMode = i;
                                            bytePos = bytePos11 + 1;
                                            break;
                                        }
                                    case SCSU.UQUOTEU /* 240 */:
                                        if (bytePos11 >= byteBufferLimit - 1) {
                                            int bytePos13 = bytePos11 - 1;
                                            System.arraycopy(byteBuffer, bytePos13, this.fBuffer, i, byteBufferLimit - bytePos13);
                                            int i13 = byteBufferLimit - bytePos13;
                                            this.fBufferLength = i13;
                                            bytePos = bytePos13 + i13;
                                            break;
                                        } else {
                                            int bytePos14 = bytePos11 + 1;
                                            bytePos = bytePos14 + 1;
                                            charBuffer[ucPos] = (char) ((byteBuffer[bytePos11] << 8) | (byteBuffer[bytePos14] & 255));
                                            ucPos++;
                                        }
                                    case SCSU.UDEFINEX /* 241 */:
                                        if (bytePos11 + 1 >= byteBufferLimit) {
                                            int bytePos15 = bytePos11 - 1;
                                            System.arraycopy(byteBuffer, bytePos15, this.fBuffer, i, byteBufferLimit - bytePos15);
                                            int i14 = byteBufferLimit - bytePos15;
                                            this.fBufferLength = i14;
                                            bytePos = bytePos15 + i14;
                                            break;
                                        } else {
                                            int bytePos16 = bytePos11 + 1;
                                            int aByte4 = byteBuffer[bytePos11] & 255;
                                            int i15 = (aByte4 & 224) >> 5;
                                            this.fCurrentWindow = i15;
                                            this.fOffsets[i15] = (((byteBuffer[bytePos16] & 255) | ((aByte4 & 31) << 8)) * 128) + 65536;
                                            this.fMode = i;
                                            bytePos = bytePos16 + 1;
                                            break;
                                        }
                                    default:
                                        if (bytePos11 >= byteBufferLimit) {
                                            int bytePos17 = bytePos11 - 1;
                                            System.arraycopy(byteBuffer, bytePos17, this.fBuffer, i, byteBufferLimit - bytePos17);
                                            int i16 = byteBufferLimit - bytePos17;
                                            this.fBufferLength = i16;
                                            bytePos = bytePos17 + i16;
                                            break;
                                        } else {
                                            bytePos = bytePos11 + 1;
                                            charBuffer[ucPos] = (char) ((byteBuffer[bytePos11] & 255) | (aByte3 << 8));
                                            ucPos++;
                                        }
                                }
                            }
                        }
                        break;
                }
            }
        }
        if (bytesRead != null) {
            bytesRead[i] = bytePos - byteBufferStart;
        }
        return ucPos - charBufferStart;
    }

    public void reset() {
        int[] iArr = this.fOffsets;
        iArr[0] = 128;
        iArr[1] = 192;
        iArr[2] = 1024;
        iArr[3] = 1536;
        iArr[4] = 2304;
        iArr[5] = 12352;
        iArr[6] = 12448;
        iArr[7] = 65280;
        this.fCurrentWindow = 0;
        this.fMode = 0;
        this.fBufferLength = 0;
    }
}
