<?php
class TicTacToe {
    private $board;
    const BLANK = '';
    private $move_count;

    public function __construct($grid_size=3)
    {
        $this->board = array_fill(0, $grid_size, array_fill(0, $grid_size, TicTacToe::BLANK));
        $this->move_count = 0;
    }

    public function display_board()
    {
        return $this->board;
    }

    public function move($x, $y)
    {
        $this->board[$x][$y] = $this->whose_turn();
        $this->move_count++;
    }

    private function whose_turn()
    {
        return $this->move_count % 2 == 0 ? 'X' : 'O';
    }
}