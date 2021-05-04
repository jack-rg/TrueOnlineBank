package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class GUIStockPositions extends JPanel{
    JPanel panel;
    public GUIStockPositions(){
        panel = new JPanel();
        panel.setLayout(null);
        // TODO: 2021/5/2 design the Postion Interface

        JList<String> list = new JList<String>();

        list.setPreferredSize(new Dimension(200, 100));

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setListData(new String[]{"香蕉", "雪梨", "苹果", "荔枝"});
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 获取所有被选中的选项索引
                int[] indices = list.getSelectedIndices();
                // 获取选项数据的 ListModel
                ListModel<String> listModel = list.getModel();
                // 输出选中的选项
                for (int index : indices) {
                    System.out.println("选中: " + index + " = " + listModel.getElementAt(index));
                }
                System.out.println();
            }
        });

        // 设置默认选中项
        list.setSelectedIndex(1);
        // 添加到内容面板容器
        panel.add(list);
    }

    public JPanel getPanel(){
        return this.panel;
    }
}
