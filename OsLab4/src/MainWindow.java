import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import java.awt.BorderLayout;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class MainWindow {

	private JFrame frame;
	private JTextField txtName;
	private JTree tree;
	private File rootFile = new File("root",null,true);
	private File selected = rootFile;
	private File forCopy;
	private File forMove;
	private DefaultMutableTreeNode treeFile = new DefaultMutableTreeNode(rootFile);
	private DefaultMutableTreeNode selectedNodeTree = treeFile;
	private JTextField textConsol;
	private JTextField textFieldSizeDisc;
	private JTextField textSizeSector;
	private JButton btnMove;
	private JButton buttonCreateFolder;
	private JButton buttonCopy;
	private JButton buttonPaste;
	private JButton btnCreateFile;
	private JButton btnDelete;
	MyJPanel panel;
	private JTextField textFieldSizeFile;
	private JLabel lblSizeFile;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 929, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rootFile.setSize(1);
		startUpdateTree(rootFile.getChilds());
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setText("new");
		txtName.setBounds(263, 11, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setEnabled(false);
		lblName.setBounds(187, 14, 46, 14);
		frame.getContentPane().add(lblName);
		
		buttonCreateFolder = new JButton("Create folder");
		buttonCreateFolder.setEnabled(false);
		buttonCreateFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewFile(true);
			}
		});
		buttonCreateFolder.setBounds(184, 68, 165, 23);
		frame.getContentPane().add(buttonCreateFolder);
		
		textConsol = new JTextField();
		textConsol.setEnabled(false);
		textConsol.setBounds(179, 247, 169, 20);
		frame.getContentPane().add(textConsol);
		textConsol.setColumns(10);
		
		buttonCopy = new JButton("Copy");
		buttonCopy.setEnabled(false);
		buttonCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forCopy = selected;
				textConsol.setText("Copy: "+forCopy);
			}
		});
		buttonCopy.setBounds(184, 110, 165, 23);
		frame.getContentPane().add(buttonCopy);
		
		buttonPaste = new JButton("Paste");
		buttonPaste.setEnabled(false);
		buttonPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected.getFolder()) {
					try {
						File newFile = forCopy.clone();
						newFile.setParrent(selected);
						selected.getChilds().add(newFile);
						panel.allocateMemoryForFile(newFile);
						if (newFile.getFolder()) {
							copyFiles(newFile);
						}
						startUpdateTree(rootFile.getChilds());
						textConsol.setText("Complete paste");
					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
					panel.repaint();
				} else {
					textConsol.setText("Select no folder");
				}
			}
		});
		buttonPaste.setBounds(184, 144, 165, 23);
		frame.getContentPane().add(buttonPaste);
		
		btnCreateFile = new JButton("Create file");
		btnCreateFile.setEnabled(false);
		btnCreateFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateNewFile(false);
			}
		});
		btnCreateFile.setBounds(184, 42, 165, 23);
		frame.getContentPane().add(btnCreateFile);
		
		btnMove = new JButton("Move");
		btnMove.setEnabled(false);
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Objects.isNull(forMove)) {
					forMove = selected;
					textConsol.setText("choice new folder");
				} else {
					if(selected.getFolder()) {
						forMove.getParrent().getChilds().remove(forMove);
						forMove.setParrent(selected);
						forMove.getParrent().getChilds().add(forMove);
						startUpdateTree(rootFile.getChilds());
						forMove = null;
					} else {
						textConsol.setText("Select no folder");
					}
				}
			}
		});
		btnMove.setBounds(179, 179, 170, 23);
		frame.getContentPane().add(btnMove);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected == rootFile) {
					textConsol.setText("impossible");
				} else {
					selected.getParrent().getChilds().remove(selected);
					if (selected.getFolder()) {
						startDelForlder();;
					} else {
						panel.clearMemory(selected);
					}
					startUpdateTree(rootFile.getChilds());
					panel.repaint();
				}
			}
		});
		btnDelete.setBounds(189, 213, 160, 23);
		frame.getContentPane().add(btnDelete);
		
		textFieldSizeDisc = new JTextField();
		textFieldSizeDisc.setText("1024");
		textFieldSizeDisc.setBounds(263, 278, 86, 20);
		frame.getContentPane().add(textFieldSizeDisc);
		textFieldSizeDisc.setColumns(10);
		
		textSizeSector = new JTextField();
		textSizeSector.setText("4");
		textSizeSector.setBounds(263, 305, 86, 20);
		frame.getContentPane().add(textSizeSector);
		textSizeSector.setColumns(10);
		
		JLabel lblSizedisk = new JLabel("size disk");
		lblSizedisk.setEnabled(false);
		lblSizedisk.setBounds(187, 281, 46, 14);
		frame.getContentPane().add(lblSizedisk);
		
		JLabel lblSizeSector = new JLabel("size sector");
		lblSizeSector.setEnabled(false);
		lblSizeSector.setBounds(187, 308, 66, 14);
		frame.getContentPane().add(lblSizeSector);
		
		JButton btnGenerateDisc = new JButton("Set memory");
		btnGenerateDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldSizeDisc.setEditable(false);;
				textSizeSector.setEditable(false);
				btnGenerateDisc.setEnabled(false);
				btnCreateFile.setEnabled(true);
				btnDelete.setEnabled(true);
				btnMove.setEnabled(true);
				buttonCopy.setEnabled(true);
				buttonCreateFolder.setEnabled(true);
				buttonPaste.setEnabled(true);
				txtName.setEnabled(true);
				panel = new MyJPanel(Integer.parseInt(textFieldSizeDisc.getText()),Integer.parseInt(textSizeSector.getText()));
				panel.setBounds(359, 11, 544, 518);
				frame.getContentPane().add(panel);
				panel.allocateMemoryForFile(rootFile);
				panel.repaint();
				tree.setEnabled(true);
			}
		});
		btnGenerateDisc.setBounds(179, 336, 170, 23);
		frame.getContentPane().add(btnGenerateDisc);
		
		textFieldSizeFile = new JTextField();
		textFieldSizeFile.setText("10");
		textFieldSizeFile.setBounds(263, 371, 86, 20);
		frame.getContentPane().add(textFieldSizeFile);
		textFieldSizeFile.setColumns(10);
		
		lblSizeFile = new JLabel("Size File:");
		lblSizeFile.setBounds(187, 370, 46, 14);
		frame.getContentPane().add(lblSizeFile);
	}
	protected void copyFiles(File newFile) {
		for (File file : newFile.getChilds()) {
			panel.allocateMemoryForFile(file);
			if(file.getFolder()) {
				copyFiles(file);
			}
		}
	}

	private void startDelForlder() {
		panel.clearMemory(selected);
		delForder(selected.getChilds());
	}

	protected void delForder(ArrayList<File> files) {
		for (File file : files) {
			if(file.getFolder()) {
				delForder(file.getChilds());
			}
			panel.clearMemory(file);
		}
	}

	protected void startUpdateTree(ArrayList<File> childs) {
		treeFile = new DefaultMutableTreeNode(rootFile);
		updateTree(treeFile,childs);
		if(!Objects.isNull(tree)) {
			frame.getContentPane().remove(tree);
		}
		tree = new JTree(treeFile);
		tree.setEnabled(true);
		tree.setShowsRootHandles(true);
		tree.setBounds(0, 0, 169, 529);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				selectedNodeTree = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				selected =(File) selectedNodeTree.getUserObject();
				panel.setStartSelectedFile(selected.getStartInMem());
				panel.repaint();
				System.out.println(selected);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(tree);
		tree.updateUI();
		tree.setScrollsOnExpand(true);
	}

	private void updateTree(DefaultMutableTreeNode treeFile, ArrayList<File> childs) {
		for (File file : childs) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file);
			treeFile.add(newNode);
			if(file.getFolder()) {
				updateTree(newNode, file.getChilds());
			}
		}		
	}

	protected void CreateNewFile(boolean b) {
		if(selected.getFolder()) {
			File newFile = new File(txtName.getText()+"",selected,b);	
			if(b) {
				newFile.setSize(1);
			} else {
				newFile.setSize(Integer.parseInt(textFieldSizeFile.getText()));			
			}
			panel.allocateMemoryForFile(newFile);
			selected.getChilds().add(newFile);
			selectedNodeTree.add(new DefaultMutableTreeNode(newFile));
			startUpdateTree(rootFile.getChilds());
			textConsol.setText("Create "+b+" "+newFile);
		} else {
			textConsol.setText("Select no folder");
		}
		panel.repaint();
	}
}
