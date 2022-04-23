package com.google.zxing.pdf417.encoder;

import android.support.constraint.solver.widgets.Optimizer;
import android.support.v4.app.FrameMetricsAggregator;
import android.support.v4.view.InputDeviceCompat;
import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import com.ibm.icu.text.BreakIterator;
import com.wits.pms.statuscontrol.WitsCommand;

final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = {new int[]{27, 917}, new int[]{522, 568, 723, 809}, new int[]{SCSU.UDEFINE5, 308, 436, 284, 646, 653, 428, 379}, new int[]{274, 562, SCSU.UDEFINE0, 755, 599, 524, WitsCommand.SystemCommand.BENZ_CONTROL, WitsCommand.SystemCommand.PRE_SEEK_FM, 295, 116, 442, 428, 295, 42, 176, 65}, new int[]{361, 575, 922, 525, 176, 586, 640, 321, 536, 742, 677, 742, 687, 284, 193, 517, 273, 494, Optimizer.OPTIMIZATION_STANDARD, 147, 593, 800, 571, 320, 803, WitsCommand.SystemCommand.NEXT_DOT_SEEK_FM, SCSU.UCHANGE7, 390, 685, 330, 63, 410}, new int[]{539, 422, 6, 93, 862, 771, 453, 106, WitsCommand.SystemCommand.OPEN_F_CAM, 287, 107, 505, 733, 877, 381, WitsCommand.SystemCommand.AIRCON_CONTROL, 723, 476, 462, 172, 430, WitsCommand.SystemCommand.OPEN_CVBSDVR, 858, 822, 543, 376, FrameMetricsAggregator.EVERY_DURATION, 400, 672, 762, 283, 184, 440, 35, 519, 31, 460, 594, SCSU.UCHANGE1, 535, 517, 352, WitsCommand.SystemCommand.OPEN_AUX, 158, 651, WitsCommand.SystemCommand.UPDATE_SYSTEM, 488, 502, 648, 733, 717, 83, 404, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, 400, 925, 749, 415, 822, 93, 217, WitsCommand.SystemCommand.SET_PWM_DUTY_RATIO, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 244, 583, 620, 246, 148, 447, 631, 292, 908, 490, 704, 516, 258, 457, 907, 594, 723, 674, 292, 272, 96, 684, 432, 686, WitsCommand.SystemCommand.OPEN_DTV, 860, 569, 193, 219, 129, 186, SCSU.UDEFINE4, 287, 192, 775, 278, 173, 40, 379, 712, 463, 646, 776, 171, 491, 297, 763, 156, 732, 95, 270, 447, 90, 507, 48, SCSU.UCHANGE4, 821, 808, 898, 784, 663, 627, 378, 382, 262, 380, WitsCommand.SystemCommand.ANDROID_MODE, 754, 336, 89, 614, 87, 432, 670, 616, 157, 374, SCSU.URESERVED, 726, 600, 269, 375, 898, 845, 454, 354, 130, 814, 587, 804, 34, 211, 330, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, WitsCommand.SystemCommand.BENZ_CONTROL, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, WitsCommand.SystemCommand.LOW_POWER_VOLTAGE_WARNING, 82, 586, 708, 250, 905, 786, 138, 720, 858, 194, 311, 913, 275, 190, 375, 850, 438, 733, 194, 280, WitsCommand.SystemCommand.UPDATE_SYSTEM, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, WitsCommand.SystemCommand.LOW_POWER_VOLTAGE_WARNING, 796, WitsCommand.SystemCommand.OPEN_AUX, 540, 913, WitsCommand.SystemCommand.BENZ_CONTROL, WitsCommand.SystemCommand.MCU_UPDATE, 799, 137, 439, 418, 592, 668, 353, 859, 370, 694, 325, SCSU.UQUOTEU, 216, InputDeviceCompat.SOURCE_KEYBOARD, 284, 549, WitsCommand.SystemCommand.SAVE_TP_ALIGN_DATA, 884, 315, 70, 329, 793, 490, 274, 877, 162, 749, 812, 684, 461, 334, 376, 849, 521, 307, 291, 803, 712, 19, 358, 399, 908, 103, FrameMetricsAggregator.EVERY_DURATION, 51, 8, 517, SCSU.UCHANGE1, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, 433, 848, 585, WitsCommand.SystemCommand.LOC_SWITCH, 538, 906, 90, 2, 290, 743, 199, 655, 903, 329, 49, 802, 580, 355, 588, 188, 462, 10, WitsCommand.SystemCommand.PRE_DOT_SEEK_FM, 628, 320, 479, 130, 739, 71, Optimizer.OPTIMIZATION_STANDARD, 318, 374, WitsCommand.SystemCommand.CAR_MODE, 192, WitsCommand.SystemCommand.OPEN_AUX, 142, 673, 687, SCSU.UDEFINE2, 722, 384, 177, 752, WitsCommand.SystemCommand.OPEN_BT, 640, 455, 193, 689, 707, 805, 641, 48, 60, 732, 621, 895, 544, 261, 852, 655, 309, 697, 755, 756, 60, SCSU.UCHANGE7, 773, 434, 421, 726, 528, 503, 118, 49, 795, 32, 144, BreakIterator.WORD_IDEO_LIMIT, SCSU.UDEFINE6, 836, 394, 280, 566, 319, 9, 647, 550, 73, 914, 342, 126, 32, 681, 331, 792, 620, 60, WitsCommand.SystemCommand.OPEN_CVBSDVR, 441, 180, 791, 893, 754, WitsCommand.SystemCommand.OPEN_AUX, 383, SCSU.UCHANGE4, 749, 760, 213, 54, 297, WitsCommand.SystemCommand.PRE_DOT_SEEK_FM, 54, 834, 299, 922, 191, 910, 532, WitsCommand.SystemCommand.OPEN_CVBSDVR, 829, 189, 20, 167, 29, 872, 449, 83, 402, 41, 656, 505, 579, 481, 173, 404, SCSU.GREEKINDEX, 688, 95, 497, 555, 642, 543, 307, 159, 924, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, WitsCommand.SystemCommand.REBOOT_MACHINE, 409, 574, 118, 498, 285, 380, 350, 492, 197, 265, 920, 155, 914, 299, SCSU.UCHANGE5, 643, 294, 871, 306, 88, 87, 193, 352, 781, 846, 75, 327, 520, 435, 543, WitsCommand.SystemCommand.UPDATE_POWER_VOLTAGE, 666, SCSU.LATININDEX, 346, 781, 621, 640, 268, 794, 534, 539, 781, 408, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, 122, 272, 383, 800, 485, 98, 752, 472, 761, 107, 784, 860, 658, 741, 290, WitsCommand.SystemCommand.LOW_POWER_VOLTAGE_WARNING, 681, 407, 855, 85, 99, 62, 482, 180, 20, 297, 451, 593, 913, 142, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, InputDeviceCompat.SOURCE_DPAD, 192, 516, 258, SCSU.UQUOTEU, 518, 794, 395, 768, 848, 51, WitsCommand.SystemCommand.OPEN_F_CAM, 384, 168, 190, 826, 328, 596, 786, 303, 570, 381, 415, 641, 156, SCSU.UDEFINE5, 151, 429, 531, WitsCommand.SystemCommand.REBOOT_MACHINE, 676, 710, 89, 168, 304, 402, 40, 708, 575, 162, 864, SCSU.UCHANGE5, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, 707, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, 248, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, 902, 452, 167, 342, 244, 173, 35, 463, 651, 51, WitsCommand.SystemCommand.KSW_MCU_MSG, 591, 452, 578, 37, 124, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, 420, 245, 288, 594, 394, FrameMetricsAggregator.EVERY_DURATION, 327, 589, 777, WitsCommand.SystemCommand.KSW_MCU_MSG, 688, 43, 408, 842, 383, 721, 521, 560, 644, 714, 559, 62, 145, 873, 663, 713, 159, 672, 729, 624, 59, 193, 417, 158, WitsCommand.SystemCommand.SAVE_TP_ALIGN_DATA, 563, 564, 343, 693, 109, WitsCommand.SystemCommand.USING_NAVI, 563, 365, 181, 772, 677, 310, 248, 353, 708, 410, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, 424, 833, 77, 597, 346, 269, 757, 632, 695, 751, 331, 247, 184, 45, 787, 680, 18, 66, 407, 369, 54, 492, SCSU.UCHANGE4, WitsCommand.SystemCommand.AIR_DATA_REQ, 830, 922, 437, 519, 644, 905, 789, 420, 305, 441, WitsCommand.SystemCommand.REBOOT_MACHINE, 300, 892, 827, 141, 537, 381, 662, InputDeviceCompat.SOURCE_DPAD, 56, SCSU.ARMENIANINDEX, 341, SCSU.URESERVED, 797, 838, 837, 720, 224, 307, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, 425, 216, 548, SCSU.LATININDEX, 321, 881, WitsCommand.SystemCommand.KSW_MCU_MSG, 535, 673, 782, WitsCommand.SystemCommand.UPDATE_FK_CONFIG, 815, 905, 303, 843, 922, 281, 73, 469, 791, 660, 162, 498, 308, 155, 422, 907, 817, 187, 62, 16, 425, 535, 336, 286, 437, 375, 273, WitsCommand.SystemCommand.OPEN_F_CAM, 296, 183, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, 330, 5, 39, 923, 311, 424, SCSU.URESERVED, 749, 321, 54, 669, 316, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, 316, 486, 721, WitsCommand.SystemCommand.OPEN_F_CAM, 46, 656, 447, 171, 616, 464, 190, 531, 297, 321, 762, 752, 533, 175, WitsCommand.SystemCommand.PRE_DOT_SEEK_FM, 14, 381, 433, 717, 45, 111, 20, 596, 284, 736, 138, 646, 411, 877, 669, 141, 919, 45, 780, 407, 164, 332, 899, 165, 726, 600, 325, 498, 655, 357, 752, 768, 223, 849, 647, 63, 310, 863, SCSU.GREEKINDEX, 366, 304, 282, 738, 675, 410, 389, 244, 31, 121, 303, Optimizer.OPTIMIZATION_STANDARD}};

    private PDF417ErrorCorrection() {
    }

    static int getErrorCorrectionCodewordCount(int errorCorrectionLevel) {
        if (errorCorrectionLevel >= 0 && errorCorrectionLevel <= 8) {
            return 1 << (errorCorrectionLevel + 1);
        }
        throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int n) throws WriterException {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        } else if (n <= 40) {
            return 2;
        } else {
            if (n <= 160) {
                return 3;
            }
            if (n <= 320) {
                return 4;
            }
            if (n <= 863) {
                return 5;
            }
            throw new WriterException("No recommendation possible");
        }
    }

    static String generateErrorCorrection(CharSequence dataCodewords, int errorCorrectionLevel) {
        int errorCorrectionCodewordCount = getErrorCorrectionCodewordCount(errorCorrectionLevel);
        int k = errorCorrectionCodewordCount;
        char[] e = new char[errorCorrectionCodewordCount];
        int sld = dataCodewords.length();
        for (int i = 0; i < sld; i++) {
            int t1 = (dataCodewords.charAt(i) + e[k - 1]) % PDF417Common.NUMBER_OF_CODEWORDS;
            for (int j = k - 1; j > 0; j--) {
                e[j] = (char) ((e[j - 1] + (929 - ((EC_COEFFICIENTS[errorCorrectionLevel][j] * t1) % PDF417Common.NUMBER_OF_CODEWORDS))) % PDF417Common.NUMBER_OF_CODEWORDS);
            }
            e[0] = (char) ((929 - ((EC_COEFFICIENTS[errorCorrectionLevel][0] * t1) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder sb = new StringBuilder(k);
        for (int j2 = k - 1; j2 >= 0; j2--) {
            if (e[j2] != 0) {
                e[j2] = (char) (929 - e[j2]);
            }
            sb.append(e[j2]);
        }
        return sb.toString();
    }
}
