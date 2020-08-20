package Swing;

import javax.swing.*;

public class First {
    public static void main(String []args){
        JFrame jf = new JFrame("測試視窗");          // 建立視窗
        jf.setSize(250, 250);                       // 設定視窗大小
        jf.setLocationRelativeTo(null);             // 把視窗位置設定到螢幕中心
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 當點選視窗的關閉按鈕時退出程式（沒有這一句，程式不會退出）
//      2. 建立中間容器（面板容器）
        JPanel panel = new JPanel();                // 建立面板容器，使用預設的佈局管理器
//      3. 建立一個基本元件（按鈕），並新增到 面板容器 中
        JButton btn = new JButton("測試按鈕");
        panel.add(btn);
//      4. 把 面板容器 作為視窗的內容面板 設定到 視窗
        jf.setContentPane(panel);
//      5. 顯示視窗，前面建立的資訊都在記憶體中，通過 jf.setVisible(true) 把記憶體中的視窗顯示在螢幕上。
        jf.setVisible(true);
    }
}
