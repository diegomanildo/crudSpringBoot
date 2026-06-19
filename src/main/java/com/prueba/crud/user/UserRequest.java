package com.prueba.crud.user;

import org.antlr.v4.runtime.misc.NotNull;

public record UserRequest(
        @NotNull String name
) {}