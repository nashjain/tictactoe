<?php

include_once __DIR__. '/../vendor/autoload.php';
include_once __DIR__ . '/../TicTacToe.php';

class TicTacToeTest extends PHPUnit_Framework_TestCase
{
    private $game;

    function __construct()
    {
        $this->game = new TicTacToe(3);
    }

    public function test_all_cells_are_empty_in_a_new_game()
    {
        $this->verify_board_is(array(
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK)));
    }

    public function test_first_players_move_marks_X_on_the_board()
    {
        $this->assertEquals("continue", $this->game->move(1, 1));
        $this->verify_board_is(array(
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, 'X', TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK)));
    }

    public function test_next_players_move_marks_O_on_the_board()
    {
        $this->game->move(1, 1);
        $this->assertEquals("continue", $this->game->move(2, 2));
        $this->verify_board_is(array(
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, 'X', TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, 'O')));
    }

    public function test_board_has_to_be_minimum_3x3_grid()
    {
        $this->setExpectedException('InvalidArgumentException');
        new TicTacToe(2);
    }

    public function test_player_can_only_move_to_an_empty_cell()
    {
        $this->game->move(1, 1);
        $this->assertEquals("(1,1) is already occupied", $this->game->move(1, 1));
    }

    public function  test_first_player_with_all_symbols_in_one_row_wins()
    {
        $this->game->move(0, 0);
        $this->game->move(1, 0);
        $this->game->move(0, 1);
        $this->game->move(2, 1);
        $this->assertEquals("X won the Game!", $this->game->move(0, 2));
    }

    public function test_first_player_with_all_symbols_in_one_column_wins()
    {
        $this->game->move(1, 1);
        $this->game->move(0, 0);
        $this->game->move(2, 1);
        $this->game->move(1, 0);
        $this->game->move(2, 2);
        $this->assertEquals("O won the Game!", $this->game->move(2, 0));
    }

    public function test_first_player_with_all_symbols_in_principal_diagonal_wins()
    {
        $this->game->move(0, 0);
        $this->game->move(1, 0);
        $this->game->move(1, 1);
        $this->game->move(2, 1);
        $this->assertEquals("X won the Game!", $this->game->move(2, 2));
    }

    public function test_first_player_with_all_symbols_in_minor_diagonal_wins()
    {
        $this->game->move(0, 2);
        $this->game->move(1, 0);
        $this->game->move(1, 1);
        $this->game->move(2, 1);
        $this->assertEquals("X won the Game!", $this->game->move(2, 0));
    }

    public function  test_when_all_cells_are_filled_the_game_is_a_draw()
    {
        $this->game->move(0, 2);
        $this->game->move(1, 1);
        $this->game->move(1, 0);
        $this->game->move(2, 1);
        $this->game->move(2, 2);
        $this->game->move(0, 0);
        $this->game->move(0, 1);
        $this->game->move(1, 2);
        $this->assertEquals("Its a Draw!", $this->game->move(2, 0));
    }

    private function verify_board_is($expectedBoard)
    {
        $this->assertEquals($expectedBoard, $this->game->display_board());
    }

}
