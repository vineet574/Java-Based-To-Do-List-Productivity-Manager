import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TaskForgeGUI extends JFrame {
    private TaskManager manager;
    private DefaultListModel<Task> listModel;
    private JList<Task> taskList;

    public TaskForgeGUI() {
        super("TaskForge");
        manager = new TaskManager();
        listModel = new DefaultListModel<>();

        for (Task t : manager.getTasks()) listModel.addElement(t);

        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);

        JButton addBtn = new JButton("Add Task");
        JButton deleteBtn = new JButton("Delete");
        JButton completeBtn = new JButton("Mark Completed");

        addBtn.addActionListener(e -> addTask());
        deleteBtn.addActionListener(e -> deleteTask());
        completeBtn.addActionListener(e -> completeTask());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(completeBtn);
        buttonPanel.add(deleteBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTask() {
        String title = JOptionPane.showInputDialog(this, "Enter task title:");
        if (title == null || title.isBlank()) return;

        String[] options = {"Low", "Medium", "High"};
        String priority = (String) JOptionPane.showInputDialog(
            this, "Select priority:", "Priority", JOptionPane.PLAIN_MESSAGE, null, options, "Medium");

        String dateStr = JOptionPane.showInputDialog(this, "Enter due date (YYYY-MM-DD):");
        LocalDate dueDate = LocalDate.parse(dateStr);

        Task task = new Task(title, priority, dueDate);
        listModel.addElement(task);
        manager.addTask(task);
    }

    private void deleteTask() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            listModel.removeElement(selected);
            manager.removeTask(selected);
        }
    }

    private void completeTask() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            selected.setCompleted(true);
            taskList.repaint();
            manager.saveTasks();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskForgeGUI::new);
    }
}
