package GUI;

import core.Command;
import core.CommandTypeInfo;
import core.GameCommandType;

import java.io.BufferedReader;
import java.io.PrintStream;

public class CommandScanner {
    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputStream;

    public Command next() throws ScanException{

        try {
            //String tmp = inputReader.readLine();
            String tmp = "spawnmini 14";
            String[] input = tmp.split(" ");
            GameCommandType commandType = null;
            for (int i = 0; i < commandTypeInfos.length; i++) {
                if (commandTypeInfos[i].getName().equalsIgnoreCase(input[0])){
                    commandType = (GameCommandType) commandTypeInfos[i];
                }
            }

            for (String s: input
                 ) {
                System.err.println(s);
            }

            if (commandType == null){
                return null;
            }

            Object[] params;

           /* switch (commandType){
                case HELP:
                    return new Command(commandType, null);
                case EXIT:
                    return new Command(commandType, null);
                case ADDI:
                    if (input.length < 3){
                        throw new ScanException();
                    }
                    params = new Integer[] {Integer.parseInt(input[1]), Integer.parseInt(input[2])};
                    return new Command(commandType, params);
                case ADDF:
                    if (input.length < 3){
                        throw new ScanException();
                    }
                    params = new Float[] {Float.parseFloat(input[1]), Float.parseFloat(input[2])};
                    return new Command(commandType, params);
                case ECHO:
                    if (input.length < 3){
                        throw new ScanException();
                    }

                    params = new Object[] {(input[1]), Integer.parseInt(input[2])};
                    return new Command(commandType, params);

            }*/


            /*if ((commandType.getParamTypes() == null && input.length == 1) ||input.length != commandType.getParamTypes().length + 1){
                throw new ScanException("Wrong number of parameters!");
            }*/

            if (!(commandType.getParamTypes() == null && input.length == 1)){
                if (input.length != commandType.getParamTypes().length + 1){
                    throw new ScanException("Wrong number of parameters!");
                }
            }

            params = new Object[input.length - 1];

            for (int i = 0; i < input.length - 1; i++) {
                if (commandType.getParamTypes()[i] == Integer.class){
                    params[i] = Integer.parseInt(input[i + 1]);
                } else if (commandType.getParamTypes()[i] == Float.class){
                    params[i] = Float.parseFloat(input[i + 1]);
                } else if (commandType.getParamTypes()[i] == String.class){
                    params[i] = (input[i + 1]);
                } else if (commandType.getParamTypes()[i] == java.lang.Character.class){
                    params[i] = (input[i + 1].charAt(0));
                } else {
                    params[i] = input[i + 1];
                }
            }




            return new Command(commandType, params);






        } catch (Exception e) {
            if (e.getClass().equals(NumberFormatException.class)){
                throw new ScanException("Wrong formation of numericals!");
            }
            e.printStackTrace();
        }

        throw new ScanException("Wrong number of parameters!");
    }

    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
    }

}
