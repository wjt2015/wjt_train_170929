import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.io.LineProcessor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author：jintao.wang Date: 17-8-1 Time: 下午6:31
 */
public class LineProcessorImpl implements LineProcessor<String> {
    private Pattern pattern;
    /**
     * nItem,每行有nItem个由pattern分割的字符串;
     */
    private int nItems;
    /**
     * aliasList,存储别名的列表;
     */
    private List<String> aliasList;

    private String nLines;

    public LineProcessorImpl(String regex,int n){
        Preconditions.checkNotNull(regex);
        Preconditions.checkArgument(regex.length() >= 1 && n >= 2);
        nItems = n;
        pattern = Pattern.compile(regex);
        aliasList = new LinkedList<String>();
        nLines = null;
    }
    /**
     * This method will be called once for each line.
     *
     * @param line the line read from the input, without delimiter
     * @return true to continue processing, false to stop
     */
    public boolean processLine(String line) throws IOException{
        Preconditions.checkNotNull(line);
        if(line.length() < 3){
            return false;
        }
        List<String> strList = Splitter.on(pattern).trimResults().omitEmptyStrings().splitToList(line);
        if(strList.size() == nItems){
            String[] strArr = new String[nItems];
            strArr = strList.toArray(strArr);

            String strCopy = strArr[1];

            strList = Splitter.on('.').trimResults().omitEmptyStrings().splitToList(strArr[1]);
            strArr = new String[1];
            strArr = strList.toArray(strArr);
            int index = strCopy.indexOf('.');
            Preconditions.checkArgument(index >= 0);

            String suffix2 = strCopy.substring(index + 1);
            addAliasFromLine(strArr[0],strArr[strArr.length-1],suffix2);
        }

        return true;
    }

    /** Return the result of processing all the lines. */
    public String getResult(){
        if(nLines == null){
            nLines = new String();
            for(String strLine:aliasList){
                nLines += (strLine + "\n");
            }
        }
        return nLines;
    }

    public List<String> getAliasList(){
        return aliasList;
    }
    /**
     *
     * @param str
     * @param suffix
     */
    private void addAliasFromLine(String str,String suffix,String suffix2) {
        Preconditions.checkNotNull(str);
        char[] charArr = str.toCharArray();
        String name = null, aliasLine = null, oldName = null;
        int index = 0, istart = 0, j = 0;
        for (; index < charArr.length; index++) {
            if (charArr[index] == '[') {
                oldName = str.substring(0,index);
                name = str.substring(istart, index);
                str = str.substring(index + 1, str.length() - 1);
                String[] numStrArr = new String[1];
                numStrArr = Splitter.on('-').trimResults().omitEmptyStrings().splitToList(str).toArray(numStrArr);
                Preconditions.checkArgument(numStrArr.length == 2);
                int minId = Integer.parseInt(numStrArr[0]);
                int maxId = Integer.parseInt(numStrArr[1]);
                int id = 0;
                for (id = minId; id <= maxId; id++) {
                    aliasLine = "alias " + name + "." + id + "." + suffix + " = \"" + oldName + "." + id + "." + suffix2 + "\"";
                    aliasList.add(aliasLine);
                }
                istart = 0;
                break;
            } else if (charArr[index] >= '0' && charArr[index] <= '9') {
                oldName = str;
                aliasLine = "alias " + str.substring(istart) + "." + suffix + " = \"" + oldName + "." + suffix2 + "\"";
                aliasList.add(aliasLine);
                istart = 0;
                break;
            } else if (charArr[index] == '-') {
                istart = index + 1;
            }
        }
        if (index == charArr.length) {
            oldName = str;
            aliasLine = "alias " + str.substring(istart) + "." + suffix + " = \"" + oldName + suffix2 + "\"";
            aliasList.add(aliasLine);
        }
    }
}



