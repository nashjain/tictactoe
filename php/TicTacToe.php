<?php
class TicTacToe
{
    private $board;
    const BLANK = '';
    private $move_count;

    public function __construct($grid_size = 3)
    {
        if ($grid_size < 3)
            throw new InvalidArgumentException("TicTacToe board size has to be minimum 3x3 grid");
        $this->referee = new Referee($grid_size);
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

        return $this->referee->is_game_over($x, $y, $this->board[$x][$y], ++$this->move_count);
    }

    private function whose_turn()
    {
        return $this->move_count % 2 == 0 ? 'X' : 'O';
    }
}

class Referee
{
    private $grid_size;
    private $row_total;
    private $col_total;

    function __construct($grid_size)
    {
        $this->grid_size = $grid_size;
        $this->row_total = array_fill(0, $grid_size, 0);
        $this->col_total = array_fill(0, $grid_size, 0);
    }

    public function is_game_over($x, $y, $symbol, $move_count)
    {
        if ($this->all_symbols_match($symbol, 'row_total', $x) || $this->all_symbols_match($symbol, 'col_total', $y)) {
            return "$symbol won the Game!";
        }
        return "continue";
    }

    private function all_symbols_match($symbol, $total, $index)
    {
        $int_val_of_symbol = ord($symbol);
        $this->$total[$index] += $int_val_of_symbol;
        return $this->$total[$index] / $this->grid_size == $int_val_of_symbol;
    }
}