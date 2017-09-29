import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author：jintao.wang Date: 17-8-1 Time: 下午8:45
 */

@Setter
@Getter
@AllArgsConstructor
public class StringTranslator {
    private LineProcessorImpl lineProcessor;

    public StringTranslator(){
        lineProcessor = new LineProcessorImpl("[\t ]",5);
    }

    public List<String> doTranslate(File f) throws IOException {
        Files.readLines(f, Charset.defaultCharset(),lineProcessor);
        return lineProcessor.getAliasList();
    }
}
