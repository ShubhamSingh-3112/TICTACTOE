import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToe extends JFrame{
    int width=500;
    int height=550;

    JLabel textLabel;
    JPanel textPanel; 
    JPanel boardPanel;
    JButton[][] board;
    boolean gameover = false ;
    int turns=0;

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    TicTacToe()
    {
        setSize(width,height);
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        textLabel = new JLabel();

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TIC-TAC-TOE");
        textLabel.setOpaque(true);

        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        boardPanel.setOpaque(true);


        board = new JButton[3][3];

        for(int r = 0 ; r < 3 ; r++)
        {
            for(int c = 0 ; c < 3 ; c++)
            {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,100));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        if(gameover) return;
                        JButton tile = (JButton) e.getSource();
                        
                        if(tile.getText() == "")
                        {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameover)
                            {currentPlayer = currentPlayer == playerX? playerO : playerX;
                            textLabel.setText(currentPlayer+"'s Turn");}
                        }
                    }
                });
            }
        }
        add(textPanel,BorderLayout.NORTH);
        add(boardPanel);
        setVisible(true);
    }
    void checkWinner()
        {
            //horizontal
            for(int r=0;r<3;r++)
            {
                if(board[r][0].getText() == "")continue;
                if(board[r][0].getText()==board[r][1].getText()&&board[r][1].getText()==board[r][2].getText())
                {
                    gameover=true;
                    for(int i=0;i<3;i++)
                    {
                        setWinner(board[r][i]);
                    }
                    return;
                }
            }

            // vertical
            for(int c = 0 ; c < 3 ; c++)
            {
                if(board[0][c].getText()=="")continue;
                if(board[0][c].getText()==board[1][c].getText()&&board[1][c].getText()==board[2][c].getText())
                {
                    gameover=true;
                    for(int i=0;i<3;i++)
                    {
                        setWinner(board[i][c]);
                    }
                    return;
                }
            }

            // diagonal

            if(board[0][0].getText()==board[1][1].getText()&&board[1][1].getText()==board[2][2].getText()&&board[0][0].getText()!="")
            {
                gameover=true;
                for(int i=0;i<3;i++)
                {
                    setWinner(board[i][i]);
                }
                return;
            }
            if(board[0][2].getText()==board[1][1].getText()&&board[1][1].getText()==board[2][0].getText()&&board[0][2].getText()!="")
            {
                gameover=true;
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                return;
            }
            if(turns==9)
            {
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        setTie(board[i][j]);
                    }

                }
                gameover=true;
            }
        }
        void setWinner(JButton tile)
        {
            tile.setBackground(Color.gray);
            tile.setForeground(Color.green);
            textLabel.setText(currentPlayer + " is the winner!!!");
        }
        void setTie(JButton tile)
        {
            tile.setForeground(Color.orange);
            tile.setBackground(Color.gray);
            textLabel.setText("It's a tie!!");
        }
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                new TicTacToe();
            }
        });
    }
}