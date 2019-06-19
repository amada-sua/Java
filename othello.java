//코드 출처 : https://m.blog.naver.com/PostView.nhn?blogId=xogooxog&logNo=40117099379&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F

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
		// ** 전체 창을 생성
		JFrame window = new JFrame();
		window.setTitle("Othello");
		window.setSize(535,595);  

		// ** 게임 베이스 생성
		base = new JLayeredPane();
   
		// ** 게임 베이스를 창에 띄운다
		window.setContentPane(base);	
		window.setVisible(true);
		
		// ** 플레이
		player = new PlayerSetting();
	}

	class PlayerSetting extends Panel implements MouseListener, MouseMotionListener
	{	
		public static final long serialVersionUID = 1L;
		public ImageIcon play1_st, play1_shadow_st, play1win;	// play1 의 말 과 쉐도우, 로고
		public ImageIcon play2_st, play2_shadow_st, play2win;	// play2 의 말 과 쉐도우, 로고
		public int play_x, play_y, turn=0;	// 돌이 노여질 좌표, 파란돌부터 시작, 승리선언을 위한 돌 개수
		public JLabel labelshadow = new JLabel();	// 쉐도우이미지 라벨
		public Pan pan;		// 오델로 판
		public JLabel imageLabel = new JLabel();	// 오델로판이미지 라벨
		public SPan span;	// 점수(스코어) 판
		public JLabel sback = new JLabel();			// 점수(스코어)판이미지 라벨
		public JLabel vs = new JLabel();			// 점수(vs)이미지 라벨
		public JLabel player1 = new JLabel();		// 점수(player1)이미지 라벨
		public JLabel player2 = new JLabel();		// 점수(player2)이미지 라벨
		public JLabel p1left = new JLabel();		// 점수(player1의 100자리) 이미지 라벨
		public JLabel p1mid = new JLabel();			// 점수(player1의 10자리) 이미지 라벨
		public JLabel p1right = new JLabel();		// 점수(player1의 1자리) 이미지 라벨
		public JLabel p2left = new JLabel();		// 점수(player2의 100자리) 이미지 라벨
		public JLabel p2mid = new JLabel();			// 점수(player2의 10자리) 이미지 라벨
		public JLabel p2right = new JLabel();		// 점수(player2의 1자리) 이미지 라벨

		public PlayerSetting()
		{
			// ** play1 의 말 과 쉐도우, 로고 파일 설정
		    play1_st = new ImageIcon("./black.png");
		    play1_shadow_st = new ImageIcon("./black_shadow.png");
			play1win = new ImageIcon("./blackwin.png");

			// ** play2 의 말 과 쉐도우, 로고 파일 설정
		    play2_st = new ImageIcon("./white.png");
		    play2_shadow_st = new ImageIcon("./white_shadow.png");
		    play2win = new ImageIcon("./whitewin.png");

		    pan = new Pan();	// 오델로판 생성
		    span = new SPan();	// 점수(스코어)판 생성
			
		    base.addMouseListener(this);	// 클릭 모션을 위한 설정
		    base.addMouseMotionListener(this);	// 무브모션을 위한 설정
		}
		
		public void xysetting()	// 좌표를 배열 크기에 맞게 자르는 함수
		{
			if(play_x < 40)		// 좌표를 배열크기(40)와 동일하게 자르기
				play_x = 0;
			else if(play_x > 520)
				play_x = 12;
			else
				play_x = (int)play_x/40;	// 소숫점 없애기
			
			if(play_y < 40)
				play_y = 0;
			else if(play_y > 520)
				play_y = 12;
			else
				play_y = (int)play_y/40;	
		}

		public void mouseMoved(MouseEvent me)	// MouseMotionListener 메소드
		{
			play_x = me.getX();	// 마우스가 있는 곳의 x좌표 얻어오기
			play_y = me.getY();	// 마우스가 있는 곳의 x좌표 얻어오기
			
			xysetting();
			shadow_Find(play_x, play_y);	// shadow_Find 함수 호출
		}
		
		public void mouseClicked(MouseEvent me)	// MouseListener 메소드
		{
			play_x = me.getX();	
			play_y = me.getY();	
			
			xysetting();
			click_Find(play_x, play_y);	// click_Find 함수 호출 
			span = new SPan();	// 점수(스코어)판 갱신
		}

		// ** 잔여 MouseMotionListener 메소드
		public void mouseDragged(MouseEvent me){}	
		
		// ** 잔여 MouseListener 메소드
		public void mouseExited(MouseEvent me){}
		public void mouseEntered(MouseEvent me){}
		public void mousePressed(MouseEvent me){}
		public void mouseReleased(MouseEvent me){}

		public void shadow_Find(int x , int y)	// 해당 좌표에 쉐도우이미지가 올수 있는지 판별하는 함수.
		{			
			if(pan.indata(x,y) < 2){}	// 이미 돌이 있는 경우
			else	// 돌이 없는 경우
			{
				if ((turn == 0 && pan.indata(x-1,y) == 1) || (turn == 1 && pan.indata(x-1,y) == 0))	// 중간 왼쪽 방향
				{
					for(int ix=x-1; ix>=0; ix--)	// 왼쪽 방향으로 돌 조사
					{
						if(pan.indata(ix,y)==2)
							break;		// 돌이 발견 되지 않으면 스톱
						if((pan.indata(x-1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x-1,y) == 0 && pan.indata(ix,y)==1))	// 반대속성의 돌이 나오면
						{
							shadow_Stone(ix+(x-ix),y,turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x-1,y-1) == 1) || (turn == 1 && pan.indata(x-1,y-1) == 0))	// 상 왼쪽 방향
				{
					for(int ix=x-1,iy=y-1; ix>=0 || iy>=0; ix-- , iy--)	// 상 왼쪽 방향으로 돌 조사
					{
						if(pan.indata(ix,iy)==2)
							break;		
						if((pan.indata(x-1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y-1) == 0 && pan.indata(ix,iy)==1))	// 반대속성의 돌이 나오면
						{
							shadow_Stone(ix+(x-ix),iy+(y-iy),turn);	// shadow_Stone 함수 호출
						}
					}
				}

				if ((turn == 0 && pan.indata(x,y-1) == 1) || (turn == 1 && pan.indata(x,y-1) == 0))	// 상 가운대 방향
				{
					for(int iy=y-1; iy>=0; iy--)	// 상 가운대 방향으로 돌 조사
					{
						if(pan.indata(x,iy)==2)
							break;	
						if((pan.indata(x,y-1) == 1 && pan.indata(x,iy)==0) || (pan.indata(x,y-1) == 0 && pan.indata(x,iy)==1))	// 반대속성의 돌이 나오면
						{
							shadow_Stone(x,iy+(y-iy),turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y-1) == 1) || (turn == 1 && pan.indata(x+1,y-1) == 0))	// 상 오른쪽 방향
				{
					for(int ix=x+1,iy=y-1; ix<=12 || iy>=0; ix++ , iy--)	// 상 오른쪽 방향으로 돌 조사
					{
						if(pan.indata(ix,iy)==2)
							break;		
						if((pan.indata(x+1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x+1,y-1) == 0 && pan.indata(ix,iy)==1))	// 반대속성의 돌이 나오면
						{
							shadow_Stone(ix+(x-ix),iy+(y-iy),turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y) == 1) || (turn == 1 && pan.indata(x+1,y) == 0))	// 중간 오른쪽 방향
				{
					for(int ix=x+1; ix<=12; ix++)	// 오른쪽 방향으로 돌 조사
					{
						if(pan.indata(ix,y)==2)
							break;		
						if((pan.indata(x+1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x+1,y) == 0 && pan.indata(ix,y)==1))	// 반대속성의 돌이 나오면
						{
								shadow_Stone(ix+(x-ix),y,turn);								
						}
					}
				}

				if ((turn == 0 && pan.indata(x+1,y+1) == 1) || (turn == 1 && pan.indata(x+1,y+1) == 0))	// 하 오른쪽 방향
				{
					for(int ix=x+1,iy=y+1; ix<=12 || iy<=12; ix++ , iy++)	// 하 오른쪽 방향으로 돌 조사
					{
						if(pan.indata(ix,iy)==2)
							break;		
						if((pan.indata(x+1,y+1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x+1,y+1) == 0 && pan.indata(ix,iy)==1))	// 반대속성의 돌이 나오면
						{
							shadow_Stone(ix+(x-ix),iy+(y-iy),turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x,y+1) == 1) || (turn == 1 && pan.indata(x,y+1) == 0))	// 하 가운데 방향
				{
					for(int iy=y+1; iy<=12; iy++)	// 하 가운데 방향으로 돌 조사
					{
						if(pan.indata(x,iy)==2)
							break;		
						if((pan.indata(x,y+1) == 1 && pan.indata(x,iy)==0) || (pan.indata(x,y+1) == 0 && pan.indata(x,iy)==1))	// 반대속성의 돌이 나오면
						{
							shadow_Stone(x,iy+(y-iy),turn);							
						}
					}
				}

				if ((turn == 0 && pan.indata(x-1,y+1) == 1) || (turn == 1 && pan.indata(x-1,y+1) == 0))	// 하 가운데 방향
				{
					for(int ix=x-1,iy=y+1; ix>=0 || iy<=12; ix-- , iy++)	// 하 가운데 방향으로 돌 조사
					{
						if(pan.indata(ix,iy)==2)
							break;		
						if((pan.indata(x-1,y+1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y+1) == 0 && pan.indata(ix,iy)==1))	// 반대속성의 돌이 나오면
						{
							shadow_Stone(ix+(x-ix),iy+(y-iy),turn);						
						}
					}
				}
			}
		}	
		
		public void click_Find(int x , int y)	// 해당 좌표에 돌을 생성하는 함수 함수.
		{
			if(pan.indata(x,y) < 2){}	// 이미 돌이 있는 경우
			else// 돌을 둔다면
			{
				if ((turn == 0 && pan.indata(x-1,y) == 1) || (turn == 1 && pan.indata(x-1,y) == 0))	// 중간 왼쪽 방향
				{
					for(int ix=x-1; ix>=0; ix--)	// 왼쪽 방향으로 돌 조사
					{
						if(pan.indata(ix,y)==2)
							break;		// 돌이 발견 되지 않으면 스톱
						if((pan.indata(x-1,y) == 1 && pan.indata(ix,y)==0) || (pan.indata(x-1,y) == 0 && pan.indata(ix,y)==1))	// 반대속성의 돌이 나오면
						{
							for(int jx=ix; jx<=x; jx++)	// 발생지점 -> 해당 좌표까지 x축의 반대속성의 돌을 해당돌로 변경
							{	
								pan.outdata(jx,y,turn);	// 게임판에 새로운 돌의 데이터 입력
								draw_Stone(jx,y,turn);	// 새로운 돌 표시
							}
							break;
						}
					}
				}

				if ((turn == 0 && pan.indata(x-1,y-1) == 1) || (turn == 1 && pan.indata(x-1,y-1) == 0))	// 상 왼쪽 방향
				{
					for(int ix=x-1,iy=y-1; ix>=0 || iy>=0; ix-- , iy--)	// 상 왼쪽 방향으로 돌 조사
					{
						if(pan.indata(ix,iy)==2)
							break;
						if((pan.indata(x-1,y-1) == 1 && pan.indata(ix,iy)==0) || (pan.indata(x-1,y-1) == 0 && pan.indata(ix,iy)==1))	// 반대속성의 돌이 나오면
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

				if ((turn == 0 && pan.indata(x,y-1) == 1) || (turn == 1 && pan.indata(x,y-1) == 0))	// 상 가운대 방향
				{
					for(int iy=y-1; iy>=0; iy--)	// 상 가운대 방향으로 돌 조사
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

				if ((turn == 0 && pan.indata(x+1,y-1) == 1) || (turn == 1 && pan.indata(x+1,y-1) == 0))	// 상 오른쪽 방향
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

				if ((turn == 0 && pan.indata(x+1,y) == 1) || (turn == 1 && pan.indata(x+1,y) == 0))	// 중간 오른쪽 방향
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

				if ((turn == 0 && pan.indata(x+1,y+1) == 1) || (turn == 1 && pan.indata(x+1,y+1) == 0))	// 하 오른쪽 방향
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

				if ((turn == 0 && pan.indata(x,y+1) == 1) || (turn == 1 && pan.indata(x,y+1) == 0))	// 하 가운데 방향
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

				if ((turn == 0 && pan.indata(x-1,y+1) == 1) || (turn == 1 && pan.indata(x-1,y+1) == 0))	// 하 왼쪽 방향
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
			
			Victory();	// Victory 함수 선언
		}

		public void shadow_Stone(int x, int y, int turn)	// 해당 좌표에  쉐도우이미지를 생성 하는 함수.
		{			
			if(turn == 0)	// player1(파란돌)이면,
			{
			   	labelshadow.setIcon(play1_shadow_st);	// 파란쉐도우이미지 출력
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
		
		public void draw_Stone(int x, int y, int turn)	// 해당 좌표에  돌을 생성 하는 함수.
		{			
			if(turn == 0)	// player1(검은돌)이면,
			{
				JLabel label = new JLabel();
				label.setIcon(play1_st);	// 검은돌 생성
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
		
		public void Victory()	// 승리 선언 함수
		{
			int play1_count=0 , play2_count=0;
			
			for(int i=0; i<13; i++)
			{
				for(int j=0; j<13; j++)
				{
					if(pan.othello_pan[i][j] == 0)
						play1_count++;	// player1(검은돌)의 개수 세기
					if(pan.othello_pan[i][j] == 1)
						play2_count++;	// player2(하얀돌)의 개수 세기
				}
			}
				
			if(play1_count + play2_count == 169)
			{
				if(play1_count > play2_count)	// player1 이 많으면
				{
					JLabel label = new JLabel();
					label.setIcon(play1win);		// player1 승리
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
		
		class Pan	// 오델로판
		{
			ImageIcon backImageIcon;	// 배경 이미지 아이콘
			public int[][] othello_pan;	// 오델로판 배열 선언
			
			public Pan()
			{
				othello_pan = new int[13][13];	// 배열을 13*13으로 오델로판 생성
				for(int i=0; i<13; i++)
				{
					for(int j=0; j<13; j++)
						othello_pan[i][j] = 2;	// 0=play1, 1=play, 2=빈자리 -> 초기화를 위한 빈자리 선언
				}
				
				othello_pan[5][5] = 1;	//게임시작시 처음 갖고있는 돌 4개 생성		
				draw_Stone(5,5,1);		
				othello_pan[6][6] = 1;
				draw_Stone(6,6,1);				
				othello_pan[5][6] = 0;
				draw_Stone(6,5,0);				
				othello_pan[6][5] = 0;
				draw_Stone(5,6,0);
		
				backImageIcon = new ImageIcon("./pan.png");	// 오델로판 배경이미지				
				imageLabel.setIcon(backImageIcon);
				imageLabel.setBounds(0, 0, 520, 520);
				base.add(imageLabel);
			}
				
			public int indata(int x, int y)	//해당 좌표에 매칭되는 배열값을 얻어오는 함수.
			{
				if(x<0 || x>12)
					return -1;
				if(y<0 || y>12)
					return -1;
				
				return othello_pan[y][x];
			}
			
			public void outdata(int x, int y, int data)	//해당 좌표에 배열값 대입.
			{
				if(x<0 || x>12)
					return ;
				if(y<0 || y>12)
					return ;
				
				othello_pan[y][x] = data;
			}
		}
		
		class SPan	// 점수(스코어)판
		{
			ImageIcon s_back, mid, player1_st, player2_st, score;	// 점수판배경, vs, player이미지, 점수
			public int[][] score_pan;	// 점수판 배열 선언
			
			public SPan()
			{
				int play1_Scount=0, play2_Scount=0, p1_left, p1_mid, p1_right, p2_left, p2_mid, p2_right;
				// player들의 점수, player들의 점수 3개로 분한(100자리,10자리,1자리)
				score_pan = new int[13][13];	// 배열을 13*13으로 점수판 생성 
				
				mid = new ImageIcon("./vs.png");	// 점수 vs 점수 의 vs 이미지 설정
				player1_st = new ImageIcon("./black.png");	// player1의 이미지 설정
				player2_st = new ImageIcon("./white.png");	// player2의 이미지 설정
				s_back = new ImageIcon("./span.png");	// 점수판 배경의 이미지 설정
				
				JLabel sback = new JLabel();	// 배경 생성
				sback.setIcon(s_back);
				sback.setBounds(0*40,13*40, 520, 40);
				base.add(sback);
				
				vs.setIcon(mid);	// vs 생성
				vs.setBounds(6*40,13*40,40,40);
		    	base.add(vs,0);
		    	
		    	player1.setIcon(player1_st);	// player1 생성
		    	player1.setBounds(2*40,13*40,40,40);
		    	base.add(player1,0);
		    	
		    	player2.setIcon(player2_st);	// player2 생성
		    	player2.setBounds(10*40,13*40,40,40);
		    	base.add(player2,0);	 
		    	
		    	for(int i=0; i<13; i++)	// 오델로판의 배열을 돌며, player들의 돌 개수 = 점수를 세어온다
				{
					for(int j=0; j<13; j++)
					{
						if(pan.othello_pan[i][j] == 0)
							play1_Scount++;
						if(pan.othello_pan[i][j] == 1)
							play2_Scount++;
					}
				}
		    	
		    	p1_left = (int)play1_Scount/100;	// player1의 점수 100자리
		    	p1_mid = (int)((play1_Scount%100)/10);	// player1의 점수 10자리
		    	p1_right = (int)((play1_Scount%100)%10);	// player1의 점수 1자리
		    	
		    	p2_left = (int)play2_Scount/100;
		    	p2_mid = (int)((play2_Scount%100)/10);
		    	p2_right = (int)((play2_Scount%100)%10);

		    	if (p1_left == 0)	// 100자리의 숫자에 맞추어 해당 숫자 이미지 불러오기
		    		score = new ImageIcon("./0.png");
		    	else if (p1_left == 1)
		    		score = new ImageIcon("./1.png");
		    	
		    	p1left.setIcon(score);	// player1의 100자리 생성
		    	p1left.setBounds(3*40,13*40,40,40);
		    	base.add(p1left,0);
		    	
		    	if (p1_mid == 0)	// 10자리의 숫자에 맞추어 해당 숫자 이미지 불러오기
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
		    	
		    	p1mid.setIcon(score);	// player1의 10자리 생성
		    	p1mid.setBounds(4*40,13*40,40,40);
		    	base.add(p1mid,0);
		    	
		    	if (p1_right == 0)	// 1자리의 숫자에 맞추어 해당 숫자 이미지 불러오기
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
		    	
		    	p1right.setIcon(score);	// player1의 10자리 생성
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
		new othello();	// othello 생성
	}
}