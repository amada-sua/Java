/**
source of code : https://m.blog.naver.com/PostView.nhn?blogId=xogooxog&logNo=40117099379&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F

<adjustment>
changed size of board (13*13->12*12)
replaced images
corrected the error:when click empty space occur passing turn (using shadow array)
adjust comments (Korean->English)
*/

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
		// ** create window
		JFrame window = new JFrame();
		window.setTitle("Othello");
		window.setSize(493,558);  

		// ** create game base
		base = new JLayeredPane();
   
		// ** set up game base on window
		window.setContentPane(base);	
		window.setVisible(true);
		
		// ** play
		player = new PlayerSetting();
	}

	class PlayerSetting extends Panel implements MouseListener, MouseMotionListener
	{	
		public static final long serialVersionUID = 1L;
		public ImageIcon play1_st, play1_shadow_st, play1win;	// play1(black) stone, shadow, win message
		public ImageIcon play2_st, play2_shadow_st, play2win;	// play2(white)
		public int play_x, play_y, turn=0;	// coordinates, turn(0=black, 1=white) (start with black turn)
		public JLabel labelshadow = new JLabel();	// shadow image
		public Pan pan;								// othello board panel
		public JLabel imageLabel = new JLabel();	// othello board image
		public SPan span;							// score board
		public JLabel sback = new JLabel();			// score board image
		public JLabel vs = new JLabel();			// vs image
		public JLabel player1 = new JLabel();		// score image
		public JLabel player2 = new JLabel();		
		public JLabel p1left = new JLabel();		// score digits
		public JLabel p1mid = new JLabel();			
		public JLabel p1right = new JLabel();		
		public JLabel p2left = new JLabel();		
		public JLabel p2mid = new JLabel();			
		public JLabel p2right = new JLabel();		
		public int[][] shadow = new int[12][12];	// to mark coordinates can put stone

		public PlayerSetting()
		{
			// ** play1(black) file image set
		    play1_st = new ImageIcon("./black.png");
		    play1_shadow_st = new ImageIcon("./black_shadow.png");
			play1win = new ImageIcon("./blackwin.png");

			// ** play2(white)
		    play2_st = new ImageIcon("./white.png");
		    play2_shadow_st = new ImageIcon("./white_shadow.png");
		    play2win = new ImageIcon("./whitewin.png");

		    pan = new Pan();	// othello board
		    span = new SPan();	// score board
			
		    base.addMouseListener(this);
		    base.addMouseMotionListener(this);
		}
		
		public void xysetting()	// to set coordinates
		{
			if(play_x < 40)	
				play_x = 0;
			else if(play_x > 480)
				play_x = 11;
			else
				play_x = (int)play_x/40;
			
			if(play_y < 40)
				play_y = 0;
			else if(play_y > 480)
				play_y = 11;
			else
				play_y = (int)play_y/40;	
		}

		public void mouseMoved(MouseEvent me)	// MouseMotionListener method
		{
			play_x = me.getX();
			play_y = me.getY();
			
			xysetting();
			shadow_set();
			shadow_draw(play_x, play_y);
		}
		
		public void mouseClicked(MouseEvent me)	// MouseListener method
		{
			play_x = me.getX();	
			play_y = me.getY();	
			
			xysetting();
			click_Find(play_x, play_y);
			span = new SPan();
		}

		// ** rest MouseMotionListener method
		public void mouseDragged(MouseEvent me){}	
		
		// ** rest MouseListener method
		public void mouseExited(MouseEvent me){}
		public void mouseEntered(MouseEvent me){}
		public void mousePressed(MouseEvent me){}
		public void mouseReleased(MouseEvent me){}

		public void shadow_set() //setting shadow array
		{
			boolean exist = false;	// to define whether can put stone
			for(int x=0; x<12; x++) {
				for(int y=0; y<12; y++) { //shadow array initialization
					shadow[x][y]=0;
				}
			}
			
			for(int x=0; x<12; x++) {
				for(int y=0; y<12; y++) {
					if(pan.indata(x,y) < 2){}	// already stone exists
					else	// no stone
					{
						if ((turn == 0 && pan.indata(x-1,y) == 1) || (turn == 1 && pan.indata(x-1,y) == 0))	// West
						{
							for(int ix=x-1; ix>=0; ix--)
							{
								if(pan.indata(ix,y)==2)
									break;		// no stone > stop
								if((pan.indata(x-1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x-1,y) == 0 && pan.indata(ix,y)==1))	// opposite stone exist
								{
									shadow[ix+(x-ix)][y] = 1;		// set shadow array
									exist = true;
								}
							}
						}

						if ((turn == 0 && pan.indata(x-1,y-1) == 1) || (turn == 1 && pan.indata(x-1,y-1) == 0))	// North-west
						{
							for(int ix=x-1,iy=y-1; ix>=0 || iy>=0; ix-- , iy--)
							{
								if(pan.indata(ix,iy)==2)
									break;		
								if((pan.indata(x-1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y-1) == 0 && pan.indata(ix,iy)==1))
								{
									shadow[ix+(x-ix)][iy+(y-iy)] = 1;
									exist = true;
								}
							}
						}

						if ((turn == 0 && pan.indata(x,y-1) == 1) || (turn == 1 && pan.indata(x,y-1) == 0))	// North
						{
							for(int iy=y-1; iy>=0; iy--)
							{
								if(pan.indata(x,iy)==2)
									break;	
								if((pan.indata(x,y-1) == 1 && pan.indata(x,iy)==0) || (pan.indata(x,y-1) == 0 && pan.indata(x,iy)==1))
								{
									shadow[x][iy+(y-iy)] = 1;
									exist = true;
								}
							}
						}

						if ((turn == 0 && pan.indata(x+1,y-1) == 1) || (turn == 1 && pan.indata(x+1,y-1) == 0))	// North-east
						{
							for(int ix=x+1,iy=y-1; ix<=11 || iy>=0; ix++ , iy--)
							{
								if(pan.indata(ix,iy)==2)
									break;		
								if((pan.indata(x+1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x+1,y-1) == 0 && pan.indata(ix,iy)==1))
								{										
									shadow[ix+(x-ix)][iy+(y-iy)] = 1;
									exist = true;
								}
							}
						}

						if ((turn == 0 && pan.indata(x+1,y) == 1) || (turn == 1 && pan.indata(x+1,y) == 0))	//	East
						{
							for(int ix=x+1; ix<=11; ix++)
							{
								if(pan.indata(ix,y)==2)
									break;		
								if((pan.indata(x+1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x+1,y) == 0 && pan.indata(ix,y)==1))
								{
									shadow[ix+(x-ix)][y] = 1;
									exist = true;
								}
							}
						}

						if ((turn == 0 && pan.indata(x+1,y+1) == 1) || (turn == 1 && pan.indata(x+1,y+1) == 0))	// South-east
						{
							for(int ix=x+1,iy=y+1; ix<=11 || iy<=11; ix++ , iy++)
							{
								if(pan.indata(ix,iy)==2)
									break;		
								if((pan.indata(x+1,y+1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x+1,y+1) == 0 && pan.indata(ix,iy)==1))
								{
									shadow[ix+(x-ix)][iy+(y-iy)] = 1;
									exist = true;
								}
							}
						}

						if ((turn == 0 && pan.indata(x,y+1) == 1) || (turn == 1 && pan.indata(x,y+1) == 0))	// South
						{
							for(int iy=y+1; iy<=11; iy++)
							{
								if(pan.indata(x,iy)==2)
									break;		
								if((pan.indata(x,y+1) == 1 && pan.indata(x,iy)==0) || (pan.indata(x,y+1) == 0 && pan.indata(x,iy)==1))
								{
									shadow[x][iy+(y-iy)] = 1;
									exist = true;
								}
							}
						}

						if ((turn == 0 && pan.indata(x-1,y+1) == 1) || (turn == 1 && pan.indata(x-1,y+1) == 0))	// South-west
						{
							for(int ix=x-1,iy=y+1; ix>=0 || iy<=11; ix-- , iy++)
							{
								if(pan.indata(ix,iy)==2)
									break;		
								if((pan.indata(x-1,y+1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y+1) == 0 && pan.indata(ix,iy)==1))
								{
									shadow[ix+(x-ix)][iy+(y-iy)] = 1;
									exist = true;
								}
							}
						}
					}
				}
			}
			
			if(exist==false) {	// if no place can put stone pass the turn
				if(turn==0)
					turn=1;
				else if(turn==1)
					turn=0;
			}
		}
		
		public void shadow_draw(int x , int y)	// draw shadow image
		{		
			if(shadow[x][y] == 1)
				shadow_Stone(x,y,turn);
		}	
		
		public void click_Find(int x , int y)	// put stone
		{
			if(pan.indata(x,y) < 2 || shadow[x][y] == 0){}	// stone already exists or cannot put stone(shadow array value == 0)
			else
			{
				if ((turn == 0 && pan.indata(x-1,y) == 1) || (turn == 1 && pan.indata(x-1,y) == 0))	// West
				{
					for(int ix=x-1; ix>=0; ix--)
					{
						if(pan.indata(ix,y)==2)
							break;		// no stones
						if((pan.indata(x-1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x-1,y) == 0 && pan.indata(ix,y)==1))	// opposite stone exists
						{
							for(int jx=ix; jx<=x; jx++)	// change stone color
							{	
								pan.outdata(jx,y,turn);	// input new stone data
								draw_Stone(jx,y,turn);	// draw stone
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x-1,y-1) == 1) || (turn == 1 && pan.indata(x-1,y-1) == 0))	// North-west
				{
					for(int ix=x-1,iy=y-1; ix>=0 || iy>=0; ix-- , iy--)
					{
						if(pan.indata(ix,iy)==2)
							break;
						if((pan.indata(x-1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y-1) == 0 && pan.indata(ix,iy)==1))
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

				if ((turn == 0 && pan.indata(x,y-1) == 1) || (turn == 1 && pan.indata(x,y-1) == 0))	// North
				{
					for(int iy=y-1; iy>=0; iy--)
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

				if ((turn == 0 && pan.indata(x+1,y-1) == 1) || (turn == 1 && pan.indata(x+1,y-1) == 0))	// North-east
				{
					for(int ix=x+1,iy=y-1; ix<=11 || iy>=0; ix++ , iy--)
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

				if ((turn == 0 && pan.indata(x+1,y) == 1) || (turn == 1 && pan.indata(x+1,y) == 0))	// East
				{
					for(int ix=x+1; ix<=11; ix++)
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

				if ((turn == 0 && pan.indata(x+1,y+1) == 1) || (turn == 1 && pan.indata(x+1,y+1) == 0))	// South-east
				{
					for(int ix=x+1,iy=y+1; ix<=11 || iy<=11; ix++ , iy++)
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

				if ((turn == 0 && pan.indata(x,y+1) == 1) || (turn == 1 && pan.indata(x,y+1) == 0))	// South
				{
					for(int iy=y+1; iy<=11; iy++)
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

				if ((turn == 0 && pan.indata(x-1,y+1) == 1) || (turn == 1 && pan.indata(x-1,y+1) == 0))	// South-west
				{
					for(int ix=x-1,iy=y+1; ix>=0 || iy<=11; ix-- , iy++)
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
				if(turn == 0)	// pass the turn
					turn = 1;
				else if(turn == 1) {
					turn = 0;
				}
			}
			
			Victory();	// check victory
		}

		public void shadow_Stone(int x, int y, int turn)	// determine shadow image's color
		{			
			if(turn == 0)
			{
			   	labelshadow.setIcon(play1_shadow_st);
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
		
		public void draw_Stone(int x, int y, int turn)	// draw stone
		{			
			if(turn == 0)
			{
				JLabel label = new JLabel();
				label.setIcon(play1_st);	
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
		
		public void Victory()	// check and declare victory
		{
			int play1_count=0 , play2_count=0;
			
			for(int i=0; i<12; i++)
			{
				for(int j=0; j<12; j++)
				{
					if(pan.othello_pan[i][j] == 0)
						play1_count++;	// count player1(black) stone
					if(pan.othello_pan[i][j] == 1)
						play2_count++;	// count player2(white) stone
				}
			}
				
			if(play1_count + play2_count == 144)
			{
				if(play1_count > play2_count)
				{
					JLabel label = new JLabel();
					label.setIcon(play1win);
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
		
		class Pan	// othello board
		{
			ImageIcon backImageIcon;	// background image icon
			public int[][] othello_pan;
			
			public Pan()
			{
				othello_pan = new int[12][12];	// create array 12 for othello board
				for(int i=0; i<12; i++)
				{
					for(int j=0; j<12; j++)
						othello_pan[i][j] = 2;	// for initial stone
				}
				
				othello_pan[5][5] = 1;	// initial four stone
				draw_Stone(5,5,1);		
				othello_pan[6][6] = 1;
				draw_Stone(6,6,1);				
				othello_pan[5][6] = 0;
				draw_Stone(6,5,0);				
				othello_pan[6][5] = 0;
				draw_Stone(5,6,0);
		
				backImageIcon = new ImageIcon("./pan.png");	// background image			
				imageLabel.setIcon(backImageIcon);
				imageLabel.setBounds(0, 0, 480, 480);
				base.add(imageLabel);
			}
				
			public int indata(int x, int y)	// get correspond array coordinates
			{
				if(x<0 || x>11)
					return -1;
				if(y<0 || y>11)
					return -1;
				
				return othello_pan[y][x];
			}
			
			public void outdata(int x, int y, int data)	// set stone data to array coordinates
			{
				if(x<0 || x>11)
					return ;
				if(y<0 || y>11)
					return ;
				
				othello_pan[y][x] = data;
			}
		}
		
		class SPan	// score board
		{
			ImageIcon s_back, mid, player1_st, player2_st, score;	// background, vs, player image, score
			public int[][] score_pan;
			
			public SPan()
			{
				int play1_Scount=0, play2_Scount=0, p1_left, p1_mid, p1_right, p2_left, p2_mid, p2_right;
				// divide score to three digits
				score_pan = new int[12][12];
				
				mid = new ImageIcon("./vs.png");
				player1_st = new ImageIcon("./black.png"); // player1(black)
				player2_st = new ImageIcon("./white.png"); // player1(white)
				s_back = new ImageIcon("./span.png");
				
				JLabel sback = new JLabel();	// create background
				sback.setIcon(s_back);
				sback.setBounds(0*40,12*40, 520, 40);
				base.add(sback);
				
				vs.setIcon(mid);	// vs
				vs.setBounds(6*40,12*40,40,40);
		    	base.add(vs,0);
		    	
		    	player1.setIcon(player1_st);	// player1(black)
		    	player1.setBounds(2*40,12*40,40,40);
		    	base.add(player1,0);
		    	
		    	player2.setIcon(player2_st);	// player2(white)
		    	player2.setBounds(10*40,12*40,40,40);
		    	base.add(player2,0);	 
		    	
		    	for(int i=0; i<12; i++)	// count score
				{
					for(int j=0; j<12; j++)
					{
						if(pan.othello_pan[i][j] == 0)
							play1_Scount++;
						if(pan.othello_pan[i][j] == 1)
							play2_Scount++;
					}
				}
		    	
		    	p1_left = (int)play1_Scount/100;	// score three digits
		    	p1_mid = (int)((play1_Scount%100)/10);	
		    	p1_right = (int)((play1_Scount%100)%10);	
		    	
		    	p2_left = (int)play2_Scount/100;
		    	p2_mid = (int)((play2_Scount%100)/10);
		    	p2_right = (int)((play2_Scount%100)%10);

		    	if (p1_left == 0)	// get correspond image
		    		score = new ImageIcon("./0.png");
		    	else if (p1_left == 1)
		    		score = new ImageIcon("./1.png");
		    	
		    	p1left.setIcon(score);	// player1 100
		    	p1left.setBounds(3*40,12*40,40,40);
		    	base.add(p1left,0);
		    	
		    	if (p1_mid == 0)
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
		    	
		    	p1mid.setIcon(score);	// player1 10
		    	p1mid.setBounds(4*40,12*40,40,40);
		    	base.add(p1mid,0);
		    	
		    	if (p1_right == 0)
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
		    	
		    	p1right.setIcon(score);	// player1 1
		    	p1right.setBounds(5*40,12*40,40,40);
		    	base.add(p1right,0);
		    	
		    	p2_left = (int)play2_Scount/100;
		    	p2_mid = (int)((play2_Scount%100)/10);
		    	p2_right = (int)((play2_Scount%100)%10);

		    	if (p2_left == 0)
		    		score = new ImageIcon("./0.png");
		    	else if (p2_left == 1)
		    		score = new ImageIcon("./1.png");
		    	
		    	p2left.setIcon(score);
		    	p2left.setBounds(7*40,12*40,40,40);
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
		    	p2mid.setBounds(8*40,12*40,40,40);
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
		    	p2right.setBounds(9*40,12*40,40,40);
		    	base.add(p2right,0);
			}
		}
	}
	
	public static void main(String [] args)
	{
		new othello();
	}
}