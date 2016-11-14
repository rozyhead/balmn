package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier

class BoardIdentifierAlreadyExistsException(boardIdentifier: BoardIdentifier) :
    Exception("accountName=${boardIdentifier.accountName.value}, boardName=${boardIdentifier.boardName.value}")
