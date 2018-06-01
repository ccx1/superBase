package com.example.administrator.superbase.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 21:03
 */

public class ArrayUtils {

    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
    public static final int INDEX_NOT_FOUND = -1;

    private ArrayUtils() {
        // Prevents instantiation
    }


    public static String toString(Object array) {
        return toString(array, "{}");
    }

    public static String toString(Object array, String stringIfNull) {
        return array == null?stringIfNull:(new ToStringBuilder(array, ToStringStyle.SIMPLE_STYLE)).append(array).toString();
    }



    /**
     * Transforms (0,1,2,2,3) to (0,1,2,3)
     */
    public static int[] deleteDuplicatedPages(int[] pages) {
        List<Integer> result  = new ArrayList<Integer>();
        int           lastInt = -1;
        for (Integer currentInt : pages) {
            if (lastInt != currentInt) {
                result.add(currentInt);
            }
            lastInt = currentInt;
        }
        int[] arrayResult = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arrayResult[i] = result.get(i);
        }
        return arrayResult;
    }

    /**
     * Transforms (0, 4, 4, 6, 6, 6, 3) into (0, 1, 1, 2, 2, 2, 3)
     */
    public static int[] calculateIndexesInDuplicateArray(int[] originalUserPages) {
        int[] result = new int[originalUserPages.length];
        if (originalUserPages.length == 0) {
            return result;
        }

        int index = 0;
        result[0] = originalUserPages[0];
        for (int i = 1; i < originalUserPages.length; i++) {
            if (originalUserPages[i] != originalUserPages[i - 1]) {
                index++;
            }
            result[i] = index;
        }

        return result;
    }

    public static String arrayToString(int[] array) {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i != array.length - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static <T> T[] clone(T[] array) {
        return array == null ? null : (T[]) (Object[]) array.clone();
    }

    public static long[] clone(long[] array) {
        return array == null ? null : (long[]) array.clone();
    }

    public static int[] clone(int[] array) {
        return array == null ? null : (int[]) array.clone();
    }

    public static short[] clone(short[] array) {
        return array == null ? null : (short[]) array.clone();
    }

    public static char[] clone(char[] array) {
        return array == null ? null : (char[]) array.clone();
    }

    public static byte[] clone(byte[] array) {
        return array == null ? null : (byte[]) array.clone();
    }

    public static double[] clone(double[] array) {
        return array == null ? null : (double[]) array.clone();
    }

    public static float[] clone(float[] array) {
        return array == null ? null : (float[]) array.clone();
    }

    public static boolean[] clone(boolean[] array) {
        return array == null ? null : (boolean[]) array.clone();
    }


    public static <T> T[] addAll(T[] array1, T... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            Class<?> type1       = array1.getClass().getComponentType();
            T[]      joinedArray = (T[]) Array.newInstance(type1, array1.length + array2.length);
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);

            try {
                System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
                return joinedArray;
            } catch (ArrayStoreException var6) {
                Class<?> type2 = array2.getClass().getComponentType();
                if (!type1.isAssignableFrom(type2)) {
                    throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName(), var6);
                } else {
                    throw var6;
                }
            }
        }
    }

    public static boolean[] addAll(boolean[] array1, boolean... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            boolean[] joinedArray = new boolean[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static char[] addAll(char[] array1, char... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            char[] joinedArray = new char[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static byte[] addAll(byte[] array1, byte... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            byte[] joinedArray = new byte[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static short[] addAll(short[] array1, short... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            short[] joinedArray = new short[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static int[] addAll(int[] array1, int... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            int[] joinedArray = new int[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static long[] addAll(long[] array1, long... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            long[] joinedArray = new long[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static float[] addAll(float[] array1, float... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            float[] joinedArray = new float[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static double[] addAll(double[] array1, double... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            double[] joinedArray = new double[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static <T> T[] add(T[] array, T element) {
        Class type;
        if (array != null) {
            type = array.getClass().getComponentType();
        } else {
            if (element == null) {
                throw new IllegalArgumentException("Arguments cannot both be null");
            }

            type = element.getClass();
        }

        T[] newArray = (T[]) copyArrayGrow1(array, type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static boolean[] add(boolean[] array, boolean element) {
        boolean[] newArray = (boolean[]) ((boolean[]) copyArrayGrow1(array, Boolean.TYPE));
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static byte[] add(byte[] array, byte element) {
        byte[] newArray = (byte[]) ((byte[]) copyArrayGrow1(array, Byte.TYPE));
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static char[] add(char[] array, char element) {
        char[] newArray = (char[]) ((char[]) copyArrayGrow1(array, Character.TYPE));
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static double[] add(double[] array, double element) {
        double[] newArray = (double[]) ((double[]) copyArrayGrow1(array, Double.TYPE));
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static float[] add(float[] array, float element) {
        float[] newArray = (float[]) ((float[]) copyArrayGrow1(array, Float.TYPE));
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static int[] add(int[] array, int element) {
        int[] newArray = (int[]) ((int[]) copyArrayGrow1(array, Integer.TYPE));
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static long[] add(long[] array, long element) {
        long[] newArray = (long[]) ((long[]) copyArrayGrow1(array, Long.TYPE));
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static short[] add(short[] array, short element) {
        short[] newArray = (short[]) ((short[]) copyArrayGrow1(array, Short.TYPE));
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    private static Object copyArrayGrow1(Object array, Class<?> newArrayComponentType) {
        if (array != null) {
            int    arrayLength = Array.getLength(array);
            Object newArray    = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        } else {
            return Array.newInstance(newArrayComponentType, 1);
        }
    }

    public static boolean isSameLength(Object[] array1, Object[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(long[] array1, long[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(int[] array1, int[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(short[] array1, short[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(char[] array1, char[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(byte[] array1, byte[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(double[] array1, double[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(float[] array1, float[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static boolean isSameLength(boolean[] array1, boolean[] array2) {
        return getLength(array1) == getLength(array2);
    }

    public static int getLength(Object array) {
        return array == null?0:Array.getLength(array);
    }

}
