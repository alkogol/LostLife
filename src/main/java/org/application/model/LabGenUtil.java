package org.application.model;

public class LabGenUtil {
    public static boolean isAloneIncludeBorder(int[][] array, int columnIndex, int fieldIndex) {
        if (!isHaveRight(array, columnIndex, fieldIndex) &&
                !isHaveLeft(array, columnIndex, fieldIndex) &&
                !isHaveBottom(array, columnIndex, fieldIndex) &&
                !isHaveTop(array, columnIndex, fieldIndex) &&
                !isHaveRightTop(array, columnIndex, fieldIndex) &&
                !isHaveLeftTop(array, columnIndex, fieldIndex) &&
                !isHaveLeftBottom(array, columnIndex, fieldIndex) &&
                !isHaveRightBottom(array, columnIndex, fieldIndex)
        ) {
            return true;
        }
        return false;
    }

    public static boolean isTopAndBottom(int[][] array, int columnIndex, int fieldIndex) {
        return isHaveTop(array, columnIndex, fieldIndex) && isHaveBottom(array, columnIndex, fieldIndex) &&
                !isHaveRight(array, columnIndex, fieldIndex) &&
                !isHaveLeft(array, columnIndex, fieldIndex) &&
                !isHaveRightBottom(array, columnIndex, fieldIndex) &&
                !isHaveLeftBottom(array, columnIndex, fieldIndex) &&
                !isHaveLeftTop(array, columnIndex, fieldIndex) &&
                isHaveRightTop(array, columnIndex, fieldIndex);
    }

    public static boolean isOnlyOne(int[][] array, int columnIndex, int fieldIndex){
        int count = 0;
        count+= isExistAndFilled(array,columnIndex+1,fieldIndex)? 1 : 0;
        count+= isExistAndFilled(array,columnIndex-1,fieldIndex)? 1 : 0;
        count+= isExistAndFilled(array,columnIndex,fieldIndex+1)? 1 : 0;
        count+= isExistAndFilled(array,columnIndex,fieldIndex-1)? 1 : 0;
        return count<2;
    }

    public static boolean isThreeInLinePattern(int[][] array, int columnIndex, int fieldIndex) {
        return isThreeInLineBottom(array, columnIndex, fieldIndex)
                || isThreeInLineLeft(array, columnIndex, fieldIndex)
                || isThreeInLineTop(array, columnIndex, fieldIndex)
                || isThreeInLineRight(array, columnIndex, fieldIndex);
    }

    public static boolean isThreeInLineBottom(int[][] array, int columnIndex, int fieldIndex) {
        boolean result = isHaveLeftBottom(array, columnIndex, fieldIndex)
                && isHaveBottom(array, columnIndex, fieldIndex)
                && isHaveRightBottom(array, columnIndex, fieldIndex)
                && !isHaveLeft(array, columnIndex, fieldIndex)
                && !isHaveRight(array, columnIndex, fieldIndex)
                && !isHaveTop(array, columnIndex, fieldIndex)
                && !isHaveLeftTop(array, columnIndex, fieldIndex)
                && !isHaveRightTop(array, columnIndex, fieldIndex);
        return result;
    }

    public static boolean isThreeInLineRight(int[][] array, int columnIndex, int fieldIndex) {
        boolean result = !isHaveLeftBottom(array, columnIndex, fieldIndex)
                && !isHaveBottom(array, columnIndex, fieldIndex)
                && isHaveRightBottom(array, columnIndex, fieldIndex)
                && !isHaveLeft(array, columnIndex, fieldIndex)
                && isHaveRight(array, columnIndex, fieldIndex)
                && !isHaveTop(array, columnIndex, fieldIndex)
                && !isHaveLeftTop(array, columnIndex, fieldIndex)
                && isHaveRightTop(array, columnIndex, fieldIndex);
        return result;
    }

    public static boolean isThreeInLineLeft(int[][] array, int columnIndex, int fieldIndex) {
        boolean result = isHaveLeftBottom(array, columnIndex, fieldIndex)
                && !isHaveBottom(array, columnIndex, fieldIndex)
                && !isHaveRightBottom(array, columnIndex, fieldIndex)
                && isHaveLeft(array, columnIndex, fieldIndex)
                && !isHaveRight(array, columnIndex, fieldIndex)
                && !isHaveTop(array, columnIndex, fieldIndex)
                && isHaveLeftTop(array, columnIndex, fieldIndex)
                && !isHaveRightTop(array, columnIndex, fieldIndex);
        return result;
    }

    public static boolean isThreeInLineTop(int[][] array, int columnIndex, int fieldIndex) {
        boolean result = !isHaveLeftBottom(array, columnIndex, fieldIndex)
                && !isHaveBottom(array, columnIndex, fieldIndex)
                && !isHaveRightBottom(array, columnIndex, fieldIndex)
                && !isHaveLeft(array, columnIndex, fieldIndex)
                && !isHaveRight(array, columnIndex, fieldIndex)
                && isHaveTop(array, columnIndex, fieldIndex)
                && isHaveLeftTop(array, columnIndex, fieldIndex)
                && isHaveRightTop(array, columnIndex, fieldIndex);
        return result;
    }

    public static boolean isHaveRightBottom(int[][] array, int columnIndex, int fieldIndex) {
        int c = columnIndex + 1;
        int f = fieldIndex + 1;
        boolean exist = isExist(array, c, f);
        return exist && array[f][c] == 1;
    }

    public static boolean isHaveLeftBottom(int[][] array, int columnIndex, int fieldIndex) {
        int c = columnIndex - 1;
        int f = fieldIndex + 1;
        boolean exist = isExist(array, c, f);
        return exist && array[f][c] == 1;
    }

    public static boolean isHaveRightTop(int[][] array, int columnIndex, int fieldIndex) {
        int c = columnIndex + 1;
        int f = fieldIndex - 1;
        boolean exist = isExist(array, c, f);
        return exist && array[f][c] == 1;
    }

    public static boolean isHaveLeftTop(int[][] array, int columnIndex, int fieldIndex) {
        int c = columnIndex - 1;
        int f = fieldIndex - 1;
        boolean exist = isExist(array, c, f);
        return exist && array[f][c] == 1;
    }

    public static boolean isExist(int[][] array, int columnIndex, int fieldIndex) {
        int length = array.length;
        if (columnIndex < 0 || fieldIndex < 0) {
            return false;
        }
        if (fieldIndex >= length) {
            return false;
        }
        if (columnIndex >= array[fieldIndex].length) {
            return false;
        }
        return true;
    }
    public static boolean isExistAndFilled(int[][] array, int columnIndex, int fieldIndex){
        boolean exist = isExist(array, columnIndex, fieldIndex);
        return exist&&array[fieldIndex][columnIndex]==1;
    }

    public static boolean isHaveTop(int[][] array, int columnIndex, int fieldIndex) {
        if (isFirstField(array, columnIndex, fieldIndex)) {
            return true;
        }
        if (array[fieldIndex - 1][columnIndex] == 1) {
            return true;
        }
        return false;
    }


    public static boolean isHaveBottom(int[][] array, int columnIndex, int fieldIndex) {
        if (isLastField(array, columnIndex, fieldIndex)) {
            return true;
        }
        if (array[fieldIndex + 1][columnIndex] == 1) {
            return true;
        }
        return false;
    }

    public static boolean isHaveLeft(int[][] array, int columnIndex, int fieldIndex) {
        if (isFirstColumn(array, columnIndex, fieldIndex)) {
            return true;
        }
        if (array[fieldIndex][columnIndex - 1] == 1) {
            return true;
        }
        return false;
    }

    public static boolean isHaveRight(int[][] array, int columnIndex, int fieldIndex) {
        if (isLastColumn(array, columnIndex, fieldIndex)) {
            return true;
        }
        if (array[fieldIndex][columnIndex + 1] == 1) {
            return true;
        }
        return false;
    }

    public static boolean isLastField(int[][] array, int columnIndex, int fieldIndex) {
        return array.length - 1 == fieldIndex;
    }

    public static boolean isFirstField(int[][] array, int columnIndex, int fieldIndex) {
        return fieldIndex == 0;
    }

    public static boolean isLastColumn(int[][] array, int columnIndex, int fieldIndex) {
        return array[fieldIndex].length - 1 == columnIndex;
    }

    public static boolean isFirstColumn(int[][] array, int columnIndex, int fieldIndex) {
        return columnIndex == 0;
    }

}
