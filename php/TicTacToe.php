<?php
class TicTacToe {
    private $board;
    const BLANK = '';
    private $move_count;

    public function __construct($grid_size=3)
    {
        if ($grid_size < 3)
            throw new InvalidArgumentException("TicTacToe board size has to be minimum 3x3 grid");
        $this->board = array_fill(0, $grid_size, array_fill(0, $grid_size, TicTacToe::BLANK));
        $this->move_count = 0;
    }

    public function display_board()
    {
        return $this->board;
    }

    public function move($x, $y)
    {
        if ($this->board[$x][$y] != TicTacToe::BLANK)
            return "($x,$y) is already occupied";
        $this->board[$x][$y] = $this->whose_turn();
        $this->move_count++;
        return "continue";
    }

    private function whose_turn()
    {
        return $this->move_count % 2 == 0 ? 'X' : 'O';
    }
}