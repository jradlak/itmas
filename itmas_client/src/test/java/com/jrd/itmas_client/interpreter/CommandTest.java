package com.jrd.itmas_client.interpreter;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jakub on 05.06.16.
 */
public class CommandTest {

    @Test
    public void commandExtractTest() {
        Command command = new Command("user 'jrd' add");
        Command commandShow = new Command("user show");
        Assert.assertTrue(Command.CommandType.USER_ADD.name().equals(command.getCommandType().name()));
        Assert.assertTrue(Command.CommandType.USER_SHOW.name().equals(commandShow.getCommandType().name()));
    }
}