//�ڵ� ��ó : https://m.blog.naver.com/PostView.nhn?blogId=xogooxog&logNo=40117099379&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F

package project_othello;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class othello extends JFrame
{
	public PlayerSetting player;
	public JLayeredPane base;
	public static final long serialVersionUID = 1L;
	
	public othello()
	{
		// ** ��ü â�� ����
		JFrame window = new JFrame();
		window.setTitle("Othello");
		window.setSize(535,595);  

		// ** ���� ���̽� ����
		base = new JLayeredPane();
   
		// ** ���� ���̽��� â�� ����
		window.setContentPane(base);	
		window.setVisible(true);
		
		// ** �÷���
		player = new PlayerSetting();
	}

	class PlayerSetting extends Panel implements MouseListener, MouseMotionListener
	{	
		public static final long serialVersionUID = 1L;
		public ImageIcon play1_st, play1_shadow_st, play1win;	// play1 �� �� �� ������, �ΰ�
		public ImageIcon play2_st, play2_shadow_st, play2win;	// play2 �� �� �� ������, �ΰ�
		public int play_x, play_y, turn=0;	// ���� �뿩�� ��ǥ, �Ķ������� ����, �¸������� ���� �� ����
		public JLabel labelshadow = new JLabel();	// �������̹��� ��
		public Pan pan;		// ������ ��
		public JLabel imageLabel = new JLabel();	// ���������̹��� ��
		public SPan span;	// ����(���ھ�) ��
		public JLabel sback = new JLabel();			// ����(���ھ�)���̹��� ��
		public JLabel vs = new JLabel();			// ����(vs)�̹��� ��
		public JLabel player1 = new JLabel();		// ����(player1)�̹��� ��
		public JLabel player2 = new JLabel();		// ����(player2)�̹��� ��
		public JLabel p1left = new JLabel();		// ����(player1�� 100�ڸ�) �̹��� ��
		public JLabel p1mid = new JLabel();			// ����(player1�� 10�ڸ�) �̹��� ��
		public JLabel p1right = new JLabel();		// ����(player1�� 1�ڸ�) �̹��� ��
		public JLabel p2left = new JLabel();		// ����(player2�� 100�ڸ�) �̹��� ��
		public JLabel p2mid = new JLabel();			// ����(player2�� 10�ڸ�) �̹��� ��
		public JLabel p2right = new JLabel();		// ����(player2�� 1�ڸ�) �̹��� ��

		public PlayerSetting()
		{
			// ** play1 �� �� �� ������, �ΰ� ���� ����
		    play1_st = new ImageIcon("./black.png");
		    play1_shadow_st = new ImageIcon("./black_shadow.png");
			play1win = new ImageIcon("./blackwin.png");

			// ** play2 �� �� �� ������, �ΰ� ���� ����
		    play2_st = new ImageIcon("./white.png");
		    play2_shadow_st = new ImageIcon("./white_shadow.png");
		    play2win = new ImageIcon("./whitewin.png");

		    pan = new Pan();	// �������� ����
		    span = new SPan();	// ����(���ھ�)�� ����
			
		    base.addMouseListener(this);	// Ŭ�� ����� ���� ����
		    base.addMouseMotionListener(this);	// �������� ���� ����
		}
		
		public void xysetting()	// ��ǥ�� �迭 ũ�⿡ �°� �ڸ��� �Լ�
		{
			if(play_x < 40)		// ��ǥ�� �迭ũ��(40)�� �����ϰ� �ڸ���
				play_x = 0;
			else if(play_x > 520)
				play_x = 12;
			else
				play_x = (int)play_x/40;	// �Ҽ��� ���ֱ�
			
			if(play_y < 40)
				play_y = 0;
			else if(play_y > 520)
				play_y = 12;
			else
				play_y = (int)play_y/40;	
		}

		public void mouseMoved(MouseEvent me)	// MouseMotionListener �޼ҵ�
		{
			play_x = me.getX();	// ���콺�� �ִ� ���� x��ǥ ������
			play_y = me.getY();	// ���콺�� �ִ� ���� x��ǥ ������
			
			xysetting();
			shadow_Find(play_x, play_y);	// shadow_Find �Լ� ȣ��
		}
		
		public void mouseClicked(MouseEvent me)	// MouseListener �޼ҵ�
		{
			play_x = me.getX();	
			play_y = me.getY();	
			
			xysetting();
			click_Find(play_x, play_y);	// click_Find �Լ� ȣ�� 
			span = new SPan();	// ����(���ھ�)�� ����
		}

		// ** �ܿ� MouseMotionListener �޼ҵ�
		public void mouseDragged(MouseEvent me){}	
		
		// ** �ܿ� MouseListener �޼ҵ�
		public void mouseExited(MouseEvent me){}
		public void mouseEntered(MouseEvent me){}
		public void mousePressed(MouseEvent me){}
		public void mouseReleased(MouseEvent me){}

		public void shadow_Find(int x , int y)	// �ش� ��ǥ�� �������̹����� �ü� �ִ��� �Ǻ��ϴ� �Լ�.
		{			
			if(pan.indata(x,y) < 2){}	// �̹� ���� �ִ� ���
			else	// ���� ���� ���
			{
				if ((turn == 0 && pan.indata(x-1,y) == 1) || (turn == 1 && pan.indata(x-1,y) == 0))	// �߰� ���� ����
				{
					for(int ix=x-1; ix>=0; ix--)	// ���� �������� �� ����
					{
						if(pan.indata(ix,y)==2)
							break;		// ���� �߰� ���� ������ ����
						if((pan.indata(x-1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x-1,y) == 0 && pan.indata(ix,y)==1))	// �ݴ�Ӽ��� ���� ������
						{
							shadow_Stone(ix+(x-ix),y,turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x-1,y-1) == 1) || (turn == 1 && pan.indata(x-1,y-1) == 0))	// �� ���� ����
				{
					for(int ix=x-1,iy=y-1; ix>=0 || iy>=0; ix-- , iy--)	// �� ���� �������� �� ����
					{
						if(pan.indata(ix,iy)==2)
							break;		
						if((pan.indata(x-1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y-1) == 0 && pan.indata(ix,iy)==1))	// �ݴ�Ӽ��� ���� ������
						{
							shadow_Stone(ix+(x-ix),iy+(y-iy),turn);	// shadow_Stone �Լ� ȣ��
						}
					}
				}

				if ((turn == 0 && pan.indata(x,y-1) == 1) || (turn == 1 && pan.indata(x,y-1) == 0))	// �� ����� ����
				{
					for(int iy=y-1; iy>=0; iy--)	// �� ����� �������� �� ����
					{
						if(pan.indata(x,iy)==2)
							break;	
						if((pan.indata(x,y-1) == 1 && pan.indata(x,iy)==0) || (pan.indata(x,y-1) == 0 && pan.indata(x,iy)==1))	// �ݴ�Ӽ��� ���� ������
						{
							shadow_Stone(x,iy+(y-iy),turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y-1) == 1) || (turn == 1 && pan.indata(x+1,y-1) == 0))	// �� ������ ����
				{
					for(int ix=x+1,iy=y-1; ix<=12 || iy>=0; ix++ , iy--)	// �� ������ �������� �� ����
					{
						if(pan.indata(ix,iy)==2)
							break;		
						if((pan.indata(x+1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x+1,y-1) == 0 && pan.indata(ix,iy)==1))	// �ݴ�Ӽ��� ���� ������
						{
							shadow_Stone(ix+(x-ix),iy+(y-iy),turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y) == 1) || (turn == 1 && pan.indata(x+1,y) == 0))	// �߰� ������ ����
				{
					for(int ix=x+1; ix<=12; ix++)	// ������ �������� �� ����
					{
						if(pan.indata(ix,y)==2)
							break;		
						if((pan.indata(x+1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x+1,y) == 0 && pan.indata(ix,y)==1))	// �ݴ�Ӽ��� ���� ������
						{
								shadow_Stone(ix+(x-ix),y,turn);								
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y+1) == 1) || (turn == 1 && pan.indata(x+1,y+1) == 0))	// �� ������ ����
				{
					for(int ix=x+1,iy=y+1; ix<=12 || iy<=12; ix++ , iy++)	// �� ������ �������� �� ����
					{
						if(pan.indata(ix,iy)==2)
							break;		
						if((pan.indata(x+1,y+1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x+1,y+1) == 0 && pan.indata(ix,iy)==1))	// �ݴ�Ӽ��� ���� ������
						{
							shadow_Stone(ix+(x-ix),iy+(y-iy),turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x,y+1) == 1) || (turn == 1 && pan.indata(x,y+1) == 0))	// �� ��� ����
				{
					for(int iy=y+1; iy<=12; iy++)	// �� ��� �������� �� ����
					{
						if(pan.indata(x,iy)==2)
							break;		
						if((pan.indata(x,y+1) == 1 && pan.indata(x,iy)==0) || (pan.indata(x,y+1) == 0 && pan.indata(x,iy)==1))	// �ݴ�Ӽ��� ���� ������
						{
							shadow_Stone(x,iy+(y-iy),turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x-1,y+1) == 1) || (turn == 1 && pan.indata(x-1,y+1) == 0))	// �� ��� ����
				{
					for(int ix=x-1,iy=y+1; ix>=0 || iy<=12; ix-- , iy++)	// �� ��� �������� �� ����
					{
						if(pan.indata(ix,iy)==2)
							break;		
						if((pan.indata(x-1,y+1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y+1) == 0 && pan.indata(ix,iy)==1))	// �ݴ�Ӽ��� ���� ������
						{
							shadow_Stone(ix+(x-ix),iy+(y-iy),turn);						
						}
					}
				}
			}
		}	
		
		public void click_Find(int x , int y)	// �ش� ��ǥ�� ���� �����ϴ� �Լ� �Լ�.
		{
			if(pan.indata(x,y) < 2){}	// �̹� ���� �ִ� ���
			else// ���� �дٸ�
			{
				if ((turn == 0 && pan.indata(x-1,y) == 1) || (turn == 1 && pan.indata(x-1,y) == 0))	// �߰� ���� ����
				{
					for(int ix=x-1; ix>=0; ix--)	// ���� �������� �� ����
					{
						if(pan.indata(ix,y)==2)
							break;		// ���� �߰� ���� ������ ����
						if((pan.indata(x-1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x-1,y) == 0 && pan.indata(ix,y)==1))	// �ݴ�Ӽ��� ���� ������
						{
							for(int jx=ix; jx<=x; jx++)	// �߻����� -> �ش� ��ǥ���� x���� �ݴ�Ӽ��� ���� �ش絹�� ����
							{	
								pan.outdata(jx,y,turn);	// �����ǿ� ���ο� ���� ������ �Է�
								draw_Stone(jx,y,turn);	// ���ο� �� ǥ��
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x-1,y-1) == 1) || (turn == 1 && pan.indata(x-1,y-1) == 0))	// �� ���� ����
				{
					for(int ix=x-1,iy=y-1; ix>=0 || iy>=0; ix-- , iy--)	// �� ���� �������� �� ����
					{
						if(pan.indata(ix,iy)==2)
							break;
						if((pan.indata(x-1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y-1) == 0 && pan.indata(ix,iy)==1))	// �ݴ�Ӽ��� ���� ������
						{
							for(int jx=ix,jy=iy ; jx<=x; jx++, jy++)
							{	
								pan.outdata(jx,jy,turn);
								draw_Stone(jx,jy,turn);
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x,y-1) == 1) || (turn == 1 && pan.indata(x,y-1) == 0))	// �� ����� ����
				{
					for(int iy=y-1; iy>=0; iy--)	// �� ����� �������� �� ����
					{
						if(pan.indata(x,iy)==2)
							break;
						if((pan.indata(x,y-1) == 1 && pan.indata(x,iy)==0) || (pan.indata(x,y-1) == 0 && pan.indata(x,iy)==1))	
						{
							for(int jy=iy; jy<=y; jy++)
							{	
								pan.outdata(x,jy,turn);	
								draw_Stone(x,jy,turn);
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y-1) == 1) || (turn == 1 && pan.indata(x+1,y-1) == 0))	// �� ������ ����
				{
					for(int ix=x+1,iy=y-1; ix<=12 || iy>=0; ix++ , iy--)
					{
						if(pan.indata(ix,iy)==2)
							break;
						if((pan.indata(x+1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x+1,y-1) == 0 && pan.indata(ix,iy)==1))	
						{
							for(int jx=ix,jy=iy ; jx>=x; jx--, jy++)
							{	
								pan.outdata(jx,jy,turn);
								draw_Stone(jx,jy,turn);	
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y) == 1) || (turn == 1 && pan.indata(x+1,y) == 0))	// �߰� ������ ����
				{
					for(int ix=x+1; ix<=12; ix++)
					{
						if(pan.indata(ix,y)==2)
							break;	
						if((pan.indata(x+1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x+1,y) == 0 && pan.indata(ix,y)==1))	
						{
							for(int jx=ix; jx>=x; jx--)	
							{	
								pan.outdata(jx,y,turn);	
								draw_Stone(jx,y,turn);	
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y+1) == 1) || (turn == 1 && pan.indata(x+1,y+1) == 0))	// �� ������ ����
				{
					for(int ix=x+1,iy=y+1; ix<=12 || iy<=12; ix++ , iy++)
					{
						if(pan.indata(ix,iy)==2)
							break;
						if((pan.indata(x+1,y+1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x+1,y+1) == 0 && pan.indata(ix,iy)==1))
						{
							for(int jx=ix,jy=iy ; jx>=x; jx--, jy--)
							{	
								pan.outdata(jx,jy,turn);
								draw_Stone(jx,jy,turn);	
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x,y+1) == 1) || (turn == 1 && pan.indata(x,y+1) == 0))	// �� ��� ����
				{
					for(int iy=y+1; iy<=12; iy++)
					{
						if(pan.indata(x,iy)==2)
							break;		
						if((pan.indata(x,y+1) == 1 && pan.indata(x,iy)==0) || (pan.indata(x,y+1) == 0 && pan.indata(x,iy)==1))	
						{
							for(int jy=iy; jy>=y; jy--)	
							{	
								pan.outdata(x,jy,turn);	
								draw_Stone(x,jy,turn);
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x-1,y+1) == 1) || (turn == 1 && pan.indata(x-1,y+1) == 0))	// �� ���� ����
				{
					for(int ix=x-1,iy=y+1; ix>=0 || iy<=12; ix-- , iy++)
					{
						if(pan.indata(ix,iy)==2)
							break;
						if((pan.indata(x-1,y+1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y+1) == 0 && pan.indata(ix,iy)==1))
						{
							for(int jx=ix,jy=iy ; jx<=x; jx++, jy--)
							{	
								pan.outdata(jx,jy,turn);
								draw_Stone(jx,jy,turn);
							}
							break;
						}
					}
				}
				if(turn == 0)
					turn = 1;
				else if(turn == 1) {
					turn = 0;
				}
			}
			
			Victory();	// Victory �Լ� ����
		}

		public void shadow_Stone(int x, int y, int turn)	// �ش� ��ǥ��  �������̹����� ���� �ϴ� �Լ�.
		{			
			if(turn == 0)	// player1(�Ķ���)�̸�,
			{
			   	labelshadow.setIcon(play1_shadow_st);	// �Ķ��������̹��� ���
			    labelshadow.setBounds(x*40,y*40,40,40);
			    base.add(labelshadow,0);
			    
			}
			else if(turn == 1)
			{
				labelshadow.setIcon(play2_shadow_st);
			    labelshadow.setBounds(x*40,y*40,40,40);
			    base.add(labelshadow,0);
			}
		}
		
		public void draw_Stone(int x, int y, int turn)	// �ش� ��ǥ��  ���� ���� �ϴ� �Լ�.
		{			
			if(turn == 0)	// player1(������)�̸�,
			{
				JLabel label = new JLabel();
				label.setIcon(play1_st);	// ������ ����
			    label.setBounds(x*40,y*40,40,40);
			    base.add(label,0);    	
			}
			else
			{
			    JLabel label = new JLabel();
			    label.setIcon(play2_st);
			    label.setBounds(x*40,y*40,40,40);
			    base.add(label,0);	
			}
		}
		
		public void Victory()	// �¸� ���� �Լ�
		{
			int play1_count=0 , play2_count=0;
			
			for(int i=0; i<13; i++)
			{
				for(int j=0; j<13; j++)
				{
					if(pan.othello_pan[i][j] == 0)
						play1_count++;	// player1(������)�� ���� ����
					if(pan.othello_pan[i][j] == 1)
						play2_count++;	// player2(�Ͼᵹ)�� ���� ����
				}
			}
				
			if(play1_count + play2_count == 169)
			{
				if(play1_count > play2_count)	// player1 �� ������
				{
					JLabel label = new JLabel();
					label.setIcon(play1win);		// player1 �¸�
					label.setBounds(0,0,520,520);
					base.add(label,0);
				}
				else if(play1_count < play2_count)
				{
					JLabel label = new JLabel();
					label.setIcon(play2win);
					label.setBounds(0,0,520,520);
					base.add(label,0);
				}
			}
			else
			{
				if(play1_count == 0)
				{
					JLabel label = new JLabel();
					label.setIcon(play2win);
					label.setBounds(0,0,520,520);
					base.add(label,0);
				}
				else if(play2_count == 0)
				{
					JLabel label = new JLabel();
					label.setIcon(play1win);		
					label.setBounds(0,0,520,520);
					base.add(label,0);
				}
			}
		}
		
		class Pan	// ��������
		{
			ImageIcon backImageIcon;	// ��� �̹��� ������
			public int[][] othello_pan;	// �������� �迭 ����
			
			public Pan()
			{
				othello_pan = new int[13][13];	// �迭�� 13*13���� �������� ����
				for(int i=0; i<13; i++)
				{
					for(int j=0; j<13; j++)
						othello_pan[i][j] = 2;	// 0=play1, 1=play, 2=���ڸ� -> �ʱ�ȭ�� ���� ���ڸ� ����
				}
				
				othello_pan[5][5] = 1;	//���ӽ��۽� ó�� �����ִ� �� 4�� ����		
				draw_Stone(5,5,1);		
				othello_pan[6][6] = 1;
				draw_Stone(6,6,1);				
				othello_pan[5][6] = 0;
				draw_Stone(6,5,0);				
				othello_pan[6][5] = 0;
				draw_Stone(5,6,0);
		
				backImageIcon = new ImageIcon("./pan.png");	// �������� ����̹���				
				imageLabel.setIcon(backImageIcon);
				imageLabel.setBounds(0, 0, 520, 520);
				base.add(imageLabel);
			}
				
			public int indata(int x, int y)	//�ش� ��ǥ�� ��Ī�Ǵ� �迭���� ������ �Լ�.
			{
				if(x<0 || x>12)
					return -1;
				if(y<0 || y>12)
					return -1;
				
				return othello_pan[y][x];
			}
			
			public void outdata(int x, int y, int data)	//�ش� ��ǥ�� �迭�� ����.
			{
				if(x<0 || x>12)
					return ;
				if(y<0 || y>12)
					return ;
				
				othello_pan[y][x] = data;
			}
		}
		
		class SPan	// ����(���ھ�)��
		{
			ImageIcon s_back, mid, player1_st, player2_st, score;	// �����ǹ��, vs, player�̹���, ����
			public int[][] score_pan;	// ������ �迭 ����
			
			public SPan()
			{
				int play1_Scount=0, play2_Scount=0, p1_left, p1_mid, p1_right, p2_left, p2_mid, p2_right;
				// player���� ����, player���� ���� 3���� ����(100�ڸ�,10�ڸ�,1�ڸ�)
				score_pan = new int[13][13];	// �迭�� 13*13���� ������ ���� 
				
				mid = new ImageIcon("./vs.png");	// ���� vs ���� �� vs �̹��� ����
				player1_st = new ImageIcon("./black.png");	// player1�� �̹��� ����
				player2_st = new ImageIcon("./white.png");	// player2�� �̹��� ����
				s_back = new ImageIcon("./span.png");	// ������ ����� �̹��� ����
				
				JLabel sback = new JLabel();	// ��� ����
				sback.setIcon(s_back);
				sback.setBounds(0*40,13*40, 520, 40);
				base.add(sback);
				
				vs.setIcon(mid);	// vs ����
				vs.setBounds(6*40,13*40,40,40);
		    	base.add(vs,0);
		    	
		    	player1.setIcon(player1_st);	// player1 ����
		    	player1.setBounds(2*40,13*40,40,40);
		    	base.add(player1,0);
		    	
		    	player2.setIcon(player2_st);	// player2 ����
		    	player2.setBounds(10*40,13*40,40,40);
		    	base.add(player2,0);	 
		    	
		    	for(int i=0; i<13; i++)	// ���������� �迭�� ����, player���� �� ���� = ������ ����´�
				{
					for(int j=0; j<13; j++)
					{
						if(pan.othello_pan[i][j] == 0)
							play1_Scount++;
						if(pan.othello_pan[i][j] == 1)
							play2_Scount++;
					}
				}
		    	
		    	p1_left = (int)play1_Scount/100;	// player1�� ���� 100�ڸ�
		    	p1_mid = (int)((play1_Scount%100)/10);	// player1�� ���� 10�ڸ�
		    	p1_right = (int)((play1_Scount%100)%10);	// player1�� ���� 1�ڸ�
		    	
		    	p2_left = (int)play2_Scount/100;
		    	p2_mid = (int)((play2_Scount%100)/10);
		    	p2_right = (int)((play2_Scount%100)%10);

		    	if (p1_left == 0)	// 100�ڸ��� ���ڿ� ���߾� �ش� ���� �̹��� �ҷ�����
		    		score = new ImageIcon("./0.png");
		    	else if (p1_left == 1)
		    		score = new ImageIcon("./1.png");
		    	
		    	p1left.setIcon(score);	// player1�� 100�ڸ� ����
		    	p1left.setBounds(3*40,13*40,40,40);
		    	base.add(p1left,0);
		    	
		    	if (p1_mid == 0)	// 10�ڸ��� ���ڿ� ���߾� �ش� ���� �̹��� �ҷ�����
		    		score = new ImageIcon("./0.png");
		    	else if (p1_mid == 1)
		    		score = new ImageIcon("./1.png");
		    	else if (p1_mid == 2)
		    		score = new ImageIcon("./2.png");
		    	else if (p1_mid == 3)
		    		score = new ImageIcon("./3.png");
		    	else if (p1_mid == 4)
		    		score = new ImageIcon("./4.png");
		    	else if (p1_mid == 5)
		    		score = new ImageIcon("./5.png");
		    	else if (p1_mid == 6)
		    		score = new ImageIcon("./6.png");
		    	else if (p1_mid == 7)
		    		score = new ImageIcon("./7.png");
		    	else if (p1_mid == 8)
		    		score = new ImageIcon("./8.png");
		    	else if (p1_mid == 9)
		    		score = new ImageIcon("./9.png");   	
		    	
		    	p1mid.setIcon(score);	// player1�� 10�ڸ� ����
		    	p1mid.setBounds(4*40,13*40,40,40);
		    	base.add(p1mid,0);
		    	
		    	if (p1_right == 0)	// 1�ڸ��� ���ڿ� ���߾� �ش� ���� �̹��� �ҷ�����
		    		score = new ImageIcon("./0.png");
		    	else if (p1_right == 1)
		    		score = new ImageIcon("./1.png");
		    	else if (p1_right == 2)
		    		score = new ImageIcon("./2.png");
		    	else if (p1_right == 3)
		    		score = new ImageIcon("./3.png");
		    	else if (p1_right == 4)
		    		score = new ImageIcon("./4.png");
		    	else if (p1_right == 5)
		    		score = new ImageIcon("./5.png");
		    	else if (p1_right == 6)
		    		score = new ImageIcon("./6.png");
		    	else if (p1_right == 7)
		    		score = new ImageIcon("./7.png");
		    	else if (p1_right == 8)
		    		score = new ImageIcon("./8.png");
		    	else if (p1_right == 9)
		    		score = new ImageIcon("./9.png");   	
		    	
		    	p1right.setIcon(score);	// player1�� 10�ڸ� ����
		    	p1right.setBounds(5*40,13*40,40,40);
		    	base.add(p1right,0);
		    	
		    	p2_left = (int)play2_Scount/100;
		    	p2_mid = (int)((play2_Scount%100)/10);
		    	p2_right = (int)((play2_Scount%100)%10);

		    	if (p2_left == 0)
		    		score = new ImageIcon("./0.png");
		    	else if (p2_left == 1)
		    		score = new ImageIcon("./1.png");
		    	
		    	p2left.setIcon(score);
		    	p2left.setBounds(7*40,13*40,40,40);
		    	base.add(p2left,0);
		    	
		    	if (p2_mid == 0)
		    		score = new ImageIcon("./0.png");
		    	else if (p2_mid == 1)
		    		score = new ImageIcon("./1.png");
		    	else if (p2_mid == 2)
		    		score = new ImageIcon("./2.png");
		    	else if (p2_mid == 3)
		    		score = new ImageIcon("./3.png");
		    	else if (p2_mid == 4)
		    		score = new ImageIcon("./4.png");
		    	else if (p2_mid == 5)
		    		score = new ImageIcon("./5.png");
		    	else if (p2_mid == 6)
		    		score = new ImageIcon("./6.png");
		    	else if (p2_mid == 7)
		    		score = new ImageIcon("./7.png");
		    	else if (p2_mid == 8)
		    		score = new ImageIcon("./8.png");
		    	else if (p2_mid == 9)
		    		score = new ImageIcon("./9.png");   	
		    	
		    	p2mid.setIcon(score);
		    	p2mid.setBounds(8*40,13*40,40,40);
		    	base.add(p2mid,0);
		    	
		    	if (p2_right == 0)
		    		score = new ImageIcon("./0.png");
		    	else if (p2_right == 1)
		    		score = new ImageIcon("./1.png");
		    	else if (p2_right == 2)
		    		score = new ImageIcon("./2.png");
		    	else if (p2_right == 3)
		    		score = new ImageIcon("./3.png");
		    	else if (p2_right == 4)
		    		score = new ImageIcon("./4.png");
		    	else if (p2_right == 5)
		    		score = new ImageIcon("./5.png");
		    	else if (p2_right == 6)
		    		score = new ImageIcon("./6.png");
		    	else if (p2_right == 7)
		    		score = new ImageIcon("./7.png");
		    	else if (p2_right == 8)
		    		score = new ImageIcon("./8.png");
		    	else if (p2_right == 9)
		    		score = new ImageIcon("./9.png");   	
		    	
		    	p2right.setIcon(score);
		    	p2right.setBounds(9*40,13*40,40,40);
		    	base.add(p2right,0);
			}
		}
	}
	
	public static void main(String [] args)	// main
	{
		new othello();	// othello ����
	}
}