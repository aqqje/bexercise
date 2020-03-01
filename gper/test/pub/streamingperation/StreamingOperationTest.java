package pub.streamingperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by aqqje on 2020/3/1.
 */
public class StreamingOperationTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        // stream 串行流
        // 中间操作
        // filer 过渡
        // sorted 排序
        // map
        // distinct
        // 终止操作
        list.stream().filter(i->i>500).sorted((o1,o2)->o2.compareTo(o1)).limit(20).forEach(j-> System.out.println(j));
        // parallelStream 并行游
        // list.parallelStream();
    }
}
