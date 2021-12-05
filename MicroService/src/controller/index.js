'use strict';

const   express     = require('express'),
        router      = express.Router(),
        tictactoe   = require('../domain/services/tictactoe');

router.get('/', tictactoe.GetAll);
router.post('/create_game', tictactoe.CreateGame);
router.post('/join_game', tictactoe.JoinGame);
router.post('/game', tictactoe.Game);
router.get('/game', tictactoe.GetGame);

module.exports = router;