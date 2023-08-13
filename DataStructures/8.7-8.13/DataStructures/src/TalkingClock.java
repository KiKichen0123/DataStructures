//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Date;
//
///**
// * 该程序的说明如下：
// */
//public class TalkingClock {
//    private int interval;
//    private boolean beep;
//
//    public TalkingClock(int interval, boolean beep) {
//        this.interval = interval;
//        this.beep = beep;
//    }
//
//    public void start(){
//        ActionListener listener = new TimePriter(this);
//    }
//
//    public class TimePriter implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("At the tone,the time is" + new Date());
//            if(beep){
//                Toolkit.getDefaultToolkit().beep();
//            }
//        }
//    }
//}
