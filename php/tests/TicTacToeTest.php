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
        $this->assertEquals("continue", $this->game->move(1, 1));
        $this->verify_board_is(array(
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, 'X', TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK)));
    }

    public function testSecondPlayersMoveMarks_O_OnTheBoard()
    {
        $this->game->move(1, 1);
        $this->assertEquals("continue", $this->game->move(2, 2));
        $this->verify_board_is(array(
            array(TicTacToe::BLANK, TicTacToe::BLANK, TicTacToe::BLANK),
            array(TicTacToe::BLANK, 'X', TicTacToe::BLANK),
            array(TicTacToe::BLANK, TicTacToe::BLANK, 'O')));
    }

    /**
     * @expectedException InvalidArgumentException
     */
    public function testBoardHasToBeMinimum3x3Grid()
    {
        new TicTacToe(2);
    }

    public function testPlayerCanOnlyMoveToAnEmptyCell()
    {
        $this->game->move(1, 1);
        $this->assertEquals("(1,1) is already occupied", $this->game->move(1, 1));
    }

    public function  testFirstPlayerWithAllSymbolsInOneRowWins()
    {
        $this->game->move(0, 0);
        $this->game->move(1, 0);
        $this->game->move(0, 1);
        $this->game->move(2, 1);
        $this->assertEquals("X won the Game!", $this->game->move(0, 2));
    }

    public function  testFirstPlayerWithAllSymbolsInOneColumnWins()
    {
        $this->game->move(1, 1);
        $this->game->move(0, 0);
        $this->game->move(2, 1);
        $this->game->move(1, 0);
        $this->game->move(2, 2);
        $this->assertEquals("O won the Game!", $this->game->move(2, 0));
    }

    public function  testFirstPlayerWithAllSymbolsInPrincipalDiagonalWins()
    {
        $this->game->move(0, 0);
        $this->game->move(1, 0);
        $this->game->move(1, 1);
        $this->game->move(2, 1);
        $this->assertEquals("X won the Game!", $this->game->move(2, 2));
    }

    public function testFirstPlayerWithAllSymbolsInMinorDiagonalWins()
    {
        $this->game->move(0, 2);
        $this->game->move(1, 0);
        $this->game->move(1, 1);
        $this->game->move(2, 1);
        $this->assertEquals("X won the Game!", $this->game->move(2, 0));
    }

    private function verify_board_is($expectedBoard)
    {
        $this->assertEquals($expectedBoard, $this->game->display_board());
    }

}
