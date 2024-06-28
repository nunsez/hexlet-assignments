package exercise;

import java.util.List;

// BEGIN
public final class ListThread extends Thread {

    private SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (var i = 0; i < 1000; i++) {
            list.add(i);
        }
    }

}
// END
