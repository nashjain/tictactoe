<?php

include_once __DIR__ . "/../TicTacToe.php";

class TicTacToeTest extends PHPUnit_Framework_TestCase
{
    private $game;

    function __construct()
    {
        $this->game = new TicTacToe(3);
    }

    public function testAllCellsAreEmptyInANewGame()
    {
        $this->verify_board_is(array(
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK)));
    }

    public function testFirstPlayersMoveMarks_X_OnTheBoard()
    {
        $this->game->move(1, 1);
        $this->verify_board_is(array(
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, 'X', TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK)));
    }

    public function testSecondPlayersMoveMarks_O_OnTheBoard()
    {
        $this->game->move(1, 1);
        $this->game->move(2, 2);
        $this->verify_board_is(array(
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, 'X', TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, 'O')));
    }

    private function verify_board_is($expectedBoard)
    {
        $this->assertEquals($expectedBoard, $this->game->display_board());
    }

}
