/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.spf4j.base;

import java.io.IOException;
import java.io.Writer;
import static java.lang.Math.min;
import javax.annotation.Nonnull;

/**
 *
 * @author zoly
 */
public final class Strings {
    
    private Strings() { }
    
    
    public static void writeCsvElement(final String elem, final Writer writer) throws IOException {
        if (elem.contains(",")) {
            int length = elem.length();
            writer.write('"');
            for (int i = 0; i < length; i++) {
                char c = elem.charAt(i);
                if (c == '"') {
                    writer.write("\"\"");
                } else {
                    writer.write(c);
                }
            }
            writer.write('"');
        } else {
            writer.write(elem);
        }
    }
    
    
    public static String toCsvElement(final String elem) {
        if (elem.contains(",")) {
            int length = elem.length();
            StringBuilder builder = new StringBuilder(length + 2);
            builder.append('"');
            for (int i = 0; i < length; i++) {
                char c = elem.charAt(i);
                if (c == '"') {
                    builder.append("\"\"");
                } else {
                    builder.append(c);
                }
            }
            builder.append('"');
            return builder.toString();
            
        } else {
            return elem;
        }
    }
    
    public static int readCsvElement(final String fromStr, final int fromIdx,
            final StringBuilder addElemTo) {
        return readCsvElement(fromStr, fromIdx, fromStr.length(), addElemTo);
    }
    
    public static int readCsvElement(final String fromStr, final int fromIdx,
            final int maxIdx, final StringBuilder addElemTo) {
        int i = fromIdx;
        char c = fromStr.charAt(i);
        if (c == '"') {
            i++;
            while (i < maxIdx) {
                c = fromStr.charAt(i);
                if (c == '"') {
                    int nxtIdx = i + 1;
                    if (nxtIdx < maxIdx) {
                        if (fromStr.charAt(nxtIdx) == '"') {
                            addElemTo.append(c);
                            i = nxtIdx;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    addElemTo.append(c);
                }
                i++;
            }
        } else {
            while (c != ',') {
                addElemTo.append(c);
                i++;
                if (i >= maxIdx) {
                    break;
                }
                c = fromStr.charAt(i);
            }
        }
        return i;
    }
    
    /**
     * function that calculates the number of operations that are needed to
     * transform s1 into s2.
     * operations are: char add, char delete, char modify
     * @param s1
     * @param s2
     * @return the number of operations required to transfor s1 into s2
     */
    public static int distance(@Nonnull final String s1, @Nonnull final String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        int[] prev = new int[l2];
        char c1 = s1.charAt(0);
        prev[0] = distance(c1, s2.charAt(0));
        for (int j = 1; j < l2; j++) {
            prev[j] = prev[j - 1] + distance(c1, s2.charAt(j));
        }

        for (int i = 1; i < l1; i++) {
            int[] dist = new int[l2];
            c1 = s1.charAt(i);
            dist[0] = prev[i - 1] + distance(c1, s2.charAt(0));
            for (int j = 1; j < l2; j++) {
                dist[j] = min(prev[j - 1] + distance(c1, s2.charAt(j)),
                        min(prev[j] + 1, dist[j - 1] + 1));
            }
            prev = dist;
        }
        return prev[l2 - 1];
    }

    public static int distance(final char c1, final char c2) {
        if (c1 == c2) {
            return 0;
        } else {
            return 1;
        }
    }
    
    
}
