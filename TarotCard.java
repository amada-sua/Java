import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class TarotCard extends JFrame implements ActionListener {

	private JPanel contentPane;
	private ImageIcon icon = new ImageIcon("C:\\project\\back.png");
	private JButton a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p;
	private JTextPane text, text2, text3;
	private final JPanel panel = new JPanel();
	private int offset = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TarotCard frame = new TarotCard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TarotCard() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ///////////////// LEFT PANEL ///////////////////////

		panel.setBounds(0, 0, 197, 461);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("TARO GAME");
		label.setFont(new Font("Serif", Font.BOLD, 20));
		label.setBounds(33, 20, 128, 22);
		panel.add(label);

		JLabel label2 = new JLabel("JUST ENJOY :) ");
		label2.setBounds(50, 50, 100, 15);
		panel.add(label2);

		JLabel label3 = new JLabel("CARD 1 ::");
		label3.setFont(new Font("Serif", Font.ITALIC, 12));
		label3.setBounds(12, 80, 57, 15);
		panel.add(label3);

		JLabel label4 = new JLabel("CARD 2 ::");
		label4.setFont(new Font("Serif", Font.ITALIC, 12));
		label4.setBounds(12, 205, 57, 15);
		panel.add(label4);

		JLabel label5 = new JLabel("CARD 3 ::");
		label5.setFont(new Font("Serif", Font.ITALIC, 12));
		label5.setBounds(12, 330, 57, 15);
		panel.add(label5);

		text = new JTextPane();
		text.setBounds(22, 105, 152, 90);
		panel.add(text);

		text2 = new JTextPane();
		text2.setBounds(22, 230, 152, 90);
		panel.add(text2);

		text3 = new JTextPane();
		text3.setBounds(22, 355, 152, 90);
		panel.add(text3);

		// ///////////////// RIGHT PANEL ///////////////////////

		JPanel panel1 = new JPanel();
		panel1.setBounds(197, 0, 587, 461);
		contentPane.add(panel1);
		panel1.setLayout(null);

		// ///////////////// BUTTON ///////////////////////

		icon = new ImageIcon("C:\\project\\back.png");

		a = new JButton();
		a.setBounds(25, 40, 75, 110);
		offset = a.getInsets().left;
		a.setIcon(resizeIcon(icon, a.getWidth() - offset, a.getHeight()
				- offset));
		a.setBorderPainted(false);
		a.setContentAreaFilled(false);
		a.addActionListener(this);
		panel1.add(a);

		b = new JButton();
		b.setBounds(118, 40, 75, 110);
		offset = b.getInsets().left;
		b.setIcon(resizeIcon(icon, b.getWidth() - offset, b.getHeight()
				- offset));
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.addActionListener(this);
		panel1.add(b);

		c = new JButton();
		c.setBounds(213, 40, 75, 110);
		offset = c.getInsets().left;
		c.setIcon(resizeIcon(icon, c.getWidth() - offset, c.getHeight()
				- offset));
		c.setContentAreaFilled(false);
		c.setBorderPainted(false);
		c.addActionListener(this);
		panel1.add(c);

		d = new JButton();
		d.setBounds(303, 40, 75, 110);
		offset = d.getInsets().left;
		d.setIcon(resizeIcon(icon, d.getWidth() - offset, d.getHeight()
				- offset));
		d.setContentAreaFilled(false);
		d.setBorderPainted(false);
		d.addActionListener(this);
		panel1.add(d);

		e = new JButton();
		e.setBounds(395, 40, 75, 110);
		offset = e.getInsets().left;
		e.setIcon(resizeIcon(icon, e.getWidth() - offset, e.getHeight()
				- offset));
		e.setContentAreaFilled(false);
		e.setBorderPainted(false);
		e.addActionListener(this);
		panel1.add(e);

		f = new JButton();
		f.setBounds(485, 40, 75, 110);
		offset = f.getInsets().left;
		f.setIcon(resizeIcon(icon, f.getWidth() - offset, f.getHeight()
				- offset));
		f.setContentAreaFilled(false);
		f.setBorderPainted(false);
		f.addActionListener(this);
		panel1.add(f);

		g = new JButton();
		g.setBounds(25, 180, 75, 110);
		offset = g.getInsets().left;
		g.setIcon(resizeIcon(icon, g.getWidth() - offset, g.getHeight()
				- offset));
		g.setBorderPainted(false);
		g.setContentAreaFilled(false);
		g.addActionListener(this);
		panel1.add(g);

		h = new JButton();
		h.setBounds(118, 180, 75, 110);
		offset = h.getInsets().left;
		h.setIcon(resizeIcon(icon, h.getWidth() - offset, h.getHeight()
				- offset));
		h.setContentAreaFilled(false);
		h.setBorderPainted(false);
		h.addActionListener(this);
		panel1.add(h);

		i = new JButton();
		i.setBounds(213, 180, 75, 110);
		offset = i.getInsets().left;
		i.setIcon(resizeIcon(icon, i.getWidth() - offset, i.getHeight()
				- offset));
		i.setContentAreaFilled(false);
		i.setBorderPainted(false);
		i.addActionListener(this);
		panel1.add(i);

		j = new JButton();
		j.setBounds(303, 180, 75, 110);
		offset = j.getInsets().left;
		j.setIcon(resizeIcon(icon, j.getWidth() - offset, j.getHeight()
				- offset));
		j.setContentAreaFilled(false);
		j.setBorderPainted(false);
		j.addActionListener(this);
		panel1.add(j);

		k = new JButton();
		k.setBounds(395, 180, 75, 110);
		offset = k.getInsets().left;
		k.setIcon(resizeIcon(icon, k.getWidth() - offset, k.getHeight()
				- offset));
		k.setContentAreaFilled(false);
		k.setBorderPainted(false);
		k.addActionListener(this);
		panel1.add(k);

		l = new JButton();
		l.setBounds(485, 180, 75, 110);
		offset = l.getInsets().left;
		l.setIcon(resizeIcon(icon, l.getWidth() - offset, l.getHeight()
				- offset));
		l.setContentAreaFilled(false);
		l.setBorderPainted(false);
		l.addActionListener(this);
		panel1.add(l);

		m = new JButton();
		m.setBounds(25, 320, 75, 110);
		offset = m.getInsets().left;
		m.setIcon(resizeIcon(icon, m.getWidth() - offset, m.getHeight()
				- offset));
		m.setBorderPainted(false);
		m.setContentAreaFilled(false);
		m.addActionListener(this);
		panel1.add(m);

		n = new JButton();
		n.setBounds(118, 320, 75, 110);
		offset = n.getInsets().left;
		n.setIcon(resizeIcon(icon, n.getWidth() - offset, n.getHeight()
				- offset));
		n.setContentAreaFilled(false);
		n.setBorderPainted(false);
		n.addActionListener(this);
		panel1.add(n);

		o = new JButton();
		o.setBounds(213, 320, 75, 110);
		offset = o.getInsets().left;
		o.setIcon(resizeIcon(icon, o.getWidth() - offset, o.getHeight()
				- offset));
		o.setContentAreaFilled(false);
		o.setBorderPainted(false);
		o.addActionListener(this);
		panel1.add(o);

		p = new JButton();
		p.setBounds(303, 320, 75, 110);
		offset = p.getInsets().left;
		p.setIcon(resizeIcon(icon, p.getWidth() - offset, p.getHeight()
				- offset));
		p.setContentAreaFilled(false);
		p.setBorderPainted(false);
		p.addActionListener(this);
		panel1.add(p);

		// ////////////////////////////////////////////////////////

	}

	private static Icon resizeIcon(ImageIcon icon, int resizedWidth,
			int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,
				java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	public void actionPerformed(ActionEvent s) {
		// TODO Auto-generated method stub
		int count = 0;
		while (count != 3) {
			if (s.getSource() == a) {
				count++;
				icon = new ImageIcon("C:\\project\\00-Fool.jpg");
				a.setIcon(resizeIcon(icon, a.getWidth() - offset, a.getHeight()
						- offset));
				if (count == 1) {
					text.setText("<00.Fool>\na new start; "
							+ "\na beginning of adventure into uncharted territory. ");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<00.Fool>\na new start; "
							+ "\na beginning of adventure into uncharted territory. ");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<00.Fool>\na new start; "
							+ "\na beginning of adventure into uncharted territory. ");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
				System.out.println(count);

			} else if (s.getSource() == b) {
				++count;
				icon = new ImageIcon("C:\\project\\02-Priestess.png");
				b.setIcon(resizeIcon(icon, b.getWidth() - offset, b.getHeight()
						- offset));
				if (count == 1) {
					text.setText("<02.Priestess>\nobjective judgment; "
							+ "\ninvisible talent and potential");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<02.Priestess>\nobjective judgment; "
							+ "\ninvisible talent and potential");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<02.Priestess>\nobjective judgment; "
							+ "\ninvisible talent and potential");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
				System.out.println(count);

			} else if (s.getSource() == c) {
				++count;
				icon = new ImageIcon("C:\\project\\05-Swords.jpg");
				c.setIcon(resizeIcon(icon, c.getWidth() - offset, c.getHeight()
						- offset));
				if (count == 1) {
					text.setText("<05-Swords>\nBe in a state of defeat."
							+ "\nGet out of there right now.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<05-Swords>\nBe in a state of defeat."
							+ "\nGet out of there right now.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<05-Swords>\nBe in a state of defeat."
							+ "\nGet out of there right now.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
				System.out.println(count);

			} else if (s.getSource() == d) {
				++count;
				icon = new ImageIcon("C:\\project\\07-Cups.png");
				d.setIcon(resizeIcon(icon, d.getWidth() - offset, d.getHeight()
						- offset));
				if (count == 1) {
					text.setText("<07-Cups>\nGet out of your fantasy and face reality.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<07-Cups>\nGet out of your fantasy and face reality.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<07-Cups>\nGet out of your fantasy and face reality.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
				System.out.println(count);

			} else if (s.getSource() == e) {
				count++;
				icon = new ImageIcon("C:\\project\\08-Coins.png");
				e.setIcon(resizeIcon(icon, e.getWidth() - offset, e.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<08-Coins>\nA working craftsman. "
							+ "\nA demonstration of ability.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<08-Coins>\nA working craftsman. "
							+ "\nA demonstration of ability.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<08-Coins>\nA working craftsman. "
							+ "\nA demonstration of ability.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}

			} else if (s.getSource() == f) {
				count++;
				icon = new ImageIcon("C:\\project\\08-Wands.png");
				f.setIcon(resizeIcon(icon, f.getWidth() - offset, f.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<08-Wands>\nThings around you turn around faster.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<08-Wands>\nThings around you turn around faster.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<08-Wands>\nThings around you turn around faster.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == g) {
				count++;
				icon = new ImageIcon("C:\\project\\09-Wands.png");
				g.setIcon(resizeIcon(icon, g.getWidth() - offset, g.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<09-Wands>\nKeep your guard down. "
							+ "\nAccept other people's help.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<09-Wands>\nKeep your guard down. "
							+ "\nAccept other people's help.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<09-Wands>\nKeep your guard down. "
							+ "\nAccept other people's help.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == h) {
				count++;
				icon = new ImageIcon("C:\\project\\11-Justice.png");
				h.setIcon(resizeIcon(icon, h.getWidth() - offset, h.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<11-Justice>\nThe acquisition of a new sense of balance; "
							+ "\nHarmony.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<11-Justice>\nThe acquisition of a new sense of balance; "
							+ "\nHarmony.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<11-Justice>\nThe acquisition of a new sense of balance; "
							+ "\nHarmony.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == i) {
				count++;
				icon = new ImageIcon("C:\\project\\11-Wands_Devotee.png");
				i.setIcon(resizeIcon(icon, i.getWidth() - offset, i.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<11-Wands_Devotee>\nLearning and Travel");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<11-Wands_Devotee>\nLearning and Travel");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<11-Wands_Devotee>\nLearning and Travel");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == j) {
				count++;
				icon = new ImageIcon("C:\\project\\12-Wands_Artisan.png");
				j.setIcon(resizeIcon(icon, j.getWidth() - offset, j.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<12-Wands_Artisan>\ndynamic male energy");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text2.setText("<12-Wands_Artisan>\ndynamic male energy");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text3.setText("<12-Wands_Artisan>\ndynamic male energy");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == k) {
				count++;
				icon = new ImageIcon("C:\\project\\13-Coins_Hero.jpg");
				k.setIcon(resizeIcon(icon, k.getWidth() - offset, k.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<13-Coins_Hero>\na woman who wants to increase the weight "
							+ "\nof her work in life.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<13-Coins_Hero>\na woman who wants to increase the weight "
							+ "\nof her work in life.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<13-Coins_Hero>\na woman who wants to increase the weight "
							+ "\nof her work in life.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == l) {
				count++;
				icon = new ImageIcon("C:\\project\\14-Coins_Enchantress.png");
				l.setIcon(resizeIcon(icon, l.getWidth() - offset, l.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<14-Coins_Enchantress>\nA person of the highest rank "
							+ "\nin his or her profession.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<14-Coins_Enchantress>\nA person of the highest rank "
							+ "\nin his or her profession.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<14-Coins_Enchantress>\nA person of the highest rank "
							+ "\nin his or her profession.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == m) {
				count++;
				icon = new ImageIcon("C:\\project\\14-Temperance.jpg");
				m.setIcon(resizeIcon(icon, m.getWidth() - offset, m.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<14-Temperance>\nreflect on one's life"
							+ "\nMaturity");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<14-Temperance>\nreflect on one's life"
							+ "\nMaturity");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<14-Temperance>\nreflect on one's life"
							+ "\nMaturity");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == n) {
				count++;
				icon = new ImageIcon("C:\\project\\16-Shipwreck.jpg");
				n.setIcon(resizeIcon(icon, n.getWidth() - offset, n.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<16-Shipwreck>\nMisery, Poverty, Disaster, "
							+ "\nCollapse, Sudden, Misfortune.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<16-Shipwreck>\nMisery, Poverty, Disaster, "
							+ "\nCollapse, Sudden, Misfortune.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<16-Shipwreck>\nMisery, Poverty, Disaster, "
							+ "\nCollapse, Sudden, Misfortune.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == o) {
				count++;
				icon = new ImageIcon("C:\\project\\19-Sun.png");
				o.setIcon(resizeIcon(icon, o.getWidth() - offset, o.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<19-Sun>\nharmonious growth in all aspects of life");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<19-Sun>\nharmonious growth in all aspects of life");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<19-Sun>\nharmonious growth in all aspects of life");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			} else if (s.getSource() == p) {
				count++;
				icon = new ImageIcon("C:\\project\\21-Garden.jpg");
				p.setIcon(resizeIcon(icon, p.getWidth() - offset, p.getHeight()
						- offset));

				if (count == 1) {
					text.setText("<21-Garden>\none journey; "
							+ "\nanother way.");
					text.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 2) {
					text.setText("<21-Garden>\none journey; "
							+ "\nanother way.");
					text2.setForeground(Color.BLACK);
					// text.setEnabled(false);
				} else if (count == 3) {
					text.setText("<21-Garden>\none journey; "
							+ "\nanother way.");
					text3.setForeground(Color.BLACK);
					// text.setEnabled(false);
				}
			}
		}
	}

}
