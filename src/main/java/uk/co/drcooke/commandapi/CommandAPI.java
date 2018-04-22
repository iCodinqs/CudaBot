/*
 * Copyright 2018 David Cooke
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uk.co.drcooke.commandapi;

import uk.co.drcooke.commandapi.argument.lexing.CommandArgumentTokeniser;
import uk.co.drcooke.commandapi.argument.parsing.ArgumentParserLookupService;
import uk.co.drcooke.commandapi.argument.parsing.CommandArgumentConverterService;
import uk.co.drcooke.commandapi.argument.parsing.SimpleArgumentParserLookupService;
import uk.co.drcooke.commandapi.argument.parsing.SimpleCommandArgumentConverterService;
import uk.co.drcooke.commandapi.command.CommandShell;
import uk.co.drcooke.commandapi.command.SimpleCommandShell;
import uk.co.drcooke.commandapi.command.lookup.CommandNamespaceSearchingCommandLookup;
import uk.co.drcooke.commandapi.command.registry.CommandNamespaceRegistry;
import uk.co.drcooke.commandapi.command.registry.SimpleCommandNamespaceRegistry;
import uk.co.drcooke.commandapi.command.scanning.ReflectionCommandScanner;
import uk.co.drcooke.commandapi.execution.executor.SimpleCommandExecutor;

public class CommandAPI {

    public static CommandShell createSimpleCommandShell() {
        CommandNamespaceRegistry commandNamespaceRegistry = new SimpleCommandNamespaceRegistry(new ReflectionCommandScanner());
        CommandArgumentConverterService commandArgumentConverterService = new SimpleCommandArgumentConverterService(new SimpleArgumentParserLookupService(ArgumentParserLookupService.getBuiltinArgumentParsers()));
        return new SimpleCommandShell(commandNamespaceRegistry, CommandArgumentTokeniser.lexical(),
                commandArgumentConverterService, new CommandNamespaceSearchingCommandLookup(commandNamespaceRegistry),
                new SimpleCommandExecutor(commandArgumentConverterService));
    }

}