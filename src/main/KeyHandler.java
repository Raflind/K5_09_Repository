package main;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entity.Player;
import Exception.WrongUseFunctionException;
import Items.Crops;
import Items.Equipment;
import Items.Fish;
import Items.Foods;
import Map.TileManager;

public class KeyHandler implements KeyListener {
    public boolean up, down, left, right;
    public boolean enterPressed, escPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(gp.gameState == gp.titleState){
            titleState(code, e);
        }
        if(gp.gameState == gp.playState){
            playState(code);  
        }
        if(gp.gameState == gp.inventoryState){
            inventoryState(code);
        }
        if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        if(gp.gameState == gp.mapSelectState){
            mapSelectState(code);
        }
        if(gp.gameState == gp.optionState){
            optionState(code);
        }
        if(gp.gameState == gp.cookingState){
            cookingState(code);
        }
        if(gp.gameState == gp.insufficientResourcesCookingState){
            insufficientResourcesCookingState(code);
        }
        if(gp.gameState == gp.shippingBinState){
            shippingBinState(code);
        }
        if(gp.gameState == gp.fishingState){
            fishingState(code, e);
        }
        if(gp.gameState == gp.insufficientEnergyState){
            insufficientEnergyState(code);
        }
        if(gp.gameState == gp.addFuelState){
            addFuelState(code);
        }
        if(gp.gameState == gp.stoveState){
            stoveState(code);
        }

    }

    public void optionState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.subState) {
                case 0: // MAIN MENU
                    switch (gp.ui.commandNum) {
                        case 0: gp.ui.subState = 1; break; // Help
                        case 1: gp.ui.subState = 2; break; // List Object
                        case 2: gp.ui.subState = 3; break; // Statistics
                        case 3: gp.ui.subState = 4; break; // Actions
                        case 4: gp.gameState = gp.titleState; break; // Exit to Title
                        case 5: gp.gameState = gp.playState; break; // Back to game
                    }
                    break;
                default:
                    gp.ui.subState = 0;
                    break;
            }
        }

        if (gp.ui.subState == 0) {
            int maxCommandNum = 5; // 6 opsi: 0–5

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = maxCommandNum;
                }
            }

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > maxCommandNum) {
                    gp.ui.commandNum = 0;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            up = false;
        }
        if(code == KeyEvent.VK_A){
            left = false;
        }
        if(code == KeyEvent.VK_S){
            down = false;
        }
        if(code == KeyEvent.VK_D){
            right = false;
        }
    }
    
    public void inventoryState(int code){
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if(gp.ui.slotCol != 5){
                gp.ui.slotCol++;
            }
        }
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            if(gp.ui.slotRow != 5){
                gp.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
            gp.ui.isAction = false; 
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.selectItems != null && gp.ui.selectItems.isEdible()) {
                if (!gp.ui.showEatPrompt) {
                    gp.ui.showEatPrompt = true;
                } else {
                    if(gp.ui.selectItems instanceof Foods){
                        gp.player.setEnergy(gp.player.getEnergy() + ((Foods) gp.ui.selectItems).getEnergyPoints());
                    }
                    else if(gp.ui.selectItems instanceof Fish){
                        gp.player.setEnergy(gp.player.getEnergy() + 1); // Default energy gain for non-food items
                    }
                    else if(gp.ui.selectItems instanceof Crops){
                        gp.player.setEnergy(gp.player.getEnergy() + 3);
                    }
                    gp.player.inventory.removeItem(gp.ui.selectItems);
                    gp.ui.showEatPrompt = false;
                    gp.ui.selectItems = null;
                }
            }
        }
    }

    public void cookingState(int code){
        if(code == KeyEvent.VK_UP){
            if(gp.ui.selectRecipe > 0){
                gp.ui.selectRecipe--;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            if(gp.ui.selectRecipe < gp.ui.availableRecipe.size() - 1){
                gp.ui.selectRecipe++;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.selectRecipe < gp.ui.availableRecipe.size()){
                String selectedRecipe = gp.ui.availableRecipe.get(gp.ui.selectRecipe);
                if(gp.player.getEnergy() < 10){
                    gp.gameState = gp.insufficientEnergyState;
                }
                else{
                    gp.cookSelectedRecipe(selectedRecipe);
                    gp.ui.showCookingScreen = false;
                    gp.player.setEnergy(gp.player.getEnergy() - 10); // Mengurangi energi pemain
                }
            }
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
    }

    public void playState(int code){
        if(code == KeyEvent.VK_W){
            up = true;
        }
        if(code == KeyEvent.VK_A){
            left = true;
        }
        if(code == KeyEvent.VK_S){
            down = true;
        }
        if(code == KeyEvent.VK_D){
            right = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionState; 
        }
        if(code == KeyEvent.VK_I){
            gp.ui.isAction = true;
            gp.ui.isAction = true;
            gp.gameState = gp.inventoryState;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_V){
            gp.gameState = gp.mapSelectState;
        }
        if(code == KeyEvent.VK_Y && gp.ui.showVisitHousePrompt) {
            gp.tileM = gp.housePlayerMap;
            gp.player.worldX = 5*gp.tileSize;
            gp.player.worldY = 13*gp.tileSize;
            gp.ui.showVisitHousePrompt = false;
            gp.ui.inHouse = true;
        }
        if(gp.ui.inHouse && code == KeyEvent.VK_X) {
            gp.tileM = gp.farmMap;
            gp.player.worldX = 4*gp.tileSize;
            gp.player.worldY = 8*gp.tileSize;
            gp.ui.inHouse = false;
        }
        if(code == KeyEvent.VK_Z && gp.ui.showSleepPrompt) {
            gp.ui.startSleepScreen();
            gp.ui.showSleepPrompt = false;
        }
        if(code == KeyEvent.VK_C && gp.ui.showCookingScreen) {
            gp.gameState = gp.stoveState;
        }
        if(code == KeyEvent.VK_B && gp.ui.showShippingBinScreen){
            gp.gameState = gp.shippingBinState;
        }
        if(code == KeyEvent.VK_ENTER && gp.ui.showVisitHousePrompt) {
            gp.ui.showVisitHousePrompt = false;
        }
        if(code == KeyEvent.VK_V){
            gp.gameState = gp.mapSelectState;
        }
        if(code == KeyEvent.VK_F && gp.ui.showFishPrompt) {
            if(gp.player.getEnergy() < 10) {
                gp.gameState = gp.insufficientEnergyState; // Ganti ke state energi tidak cukup
            }
            else{
                gp.gameState = gp.fishingState;
                gp.ui.isGuessing = true; // Set isGuessing to true to start guessing
                gp.ui.inputBuffer = ""; // Reset input buffer
                gp.time.setMinute(gp.time.getMinute() + 15);
                gp.player.consumeEnergy(5);
            }
        }
        if(code==KeyEvent.VK_M && gp.player.interactNPC==1) {
            if(gp.npcManager.getActiveNPC().getName().equals("Abigail")) {
                gp.ui.currentDialogue = "Halo! Akulah Abigail sang petualang! Aku suka untuk\nmelakukan eksplorasi! Salam kenal ya!";
            } else if(gp.npcManager.getActiveNPC().getName().equals("Caroline")) {
                gp.ui.currentDialogue = "Halo! Aku adalah pengrajin kayu terbaik di kota ini! \nSemua orang kenal denganku! Salam kenal ya!";
            } else if(gp.npcManager.getActiveNPC().getName().equals("Dasco")) {
                gp.ui.currentDialogue = "Khukhukhu, aku adalah Mafia terbesar Spakbor Hills! \nDasco namaku!";
            } else if(gp.npcManager.getActiveNPC().getName().equals("MayorTadi")) {
                gp.ui.currentDialogue = "Heh, gw mayornya di sini. Kenalin, Mayor Tadi";
            } else if(gp.npcManager.getActiveNPC().getName().equals("Perry")) {
                gp.ui.currentDialogue = "Perkenalkan, nama saya Perry, seorang penulis. \nSenang bisa berkenalan dengan Anda!";
            } else if(gp.npcManager.getActiveNPC().getName().equals("Emily")) {
                gp.ui.currentDialogue = "Haii! Aku Emily si baik hati… Salam kenal, \nsering-sering berkunjung yaa!";
            } 
            gp.ui.isAction = true;
            gp.player.interactNPC = 0;
            gp.gameState = gp.dialogueState;
            gp.ui.initInteract = true;
        }
        if(gp.tileM.currMap.equals("Farm")) {
            if(code == KeyEvent.VK_T) {
                gp.player.tilling();
            } else if(code==KeyEvent.VK_U) {
                gp.player.untilling();
            }
        } else if (gp.tileM.currMap.equals("Forest")) {
            if(code == KeyEvent.VK_F){

            } else {
            }
        } else {
            gp.ui.showVisitHousePrompt = false;
            
        }
    }

    public void titleState(int code, KeyEvent e){
        if(gp.ui.titleScreenState == 0){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                if (gp.ui.commandNum > 0){
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                if (gp.ui.commandNum < 2){
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.ui.titleScreenState = 1;
                    gp.ui.mapSelectionNum = 0;
                }
                if(gp.ui.commandNum == 1){
                    System.out.println("Anda memilih CREDITS.");
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }
        else if(gp.ui.titleScreenState == 1){
            if(code == KeyEvent.VK_ENTER){
                if(!gp.ui.inputName.trim().isEmpty()){
                    gp.player.setName(gp.ui.inputName);
                    gp.ui.inputName = "";
                    gp.ui.titleScreenState = 2;
                    gp.ui.errorMessage = "";
                } else {
                    gp.ui.errorMessage = "Name cannot be empty!";
                    gp.ui.errorMessageTime = System.currentTimeMillis();
                }
            } else if(code == KeyEvent.VK_BACK_SPACE){
                if(gp.ui.inputName.length() > 0){
                    gp.ui.inputName = gp.ui.inputName.substring(0, gp.ui.inputName.length()-1);
                }
            } else {
                char c = e.getKeyChar();
                if((Character.isLetterOrDigit(c) || c==' ')&& gp.ui.inputName.length() < 12){
                    gp.ui.inputName += c;
                }
            }
        }
        else if(gp.ui.titleScreenState == 2){
            if(code == KeyEvent.VK_ENTER){
                if(!gp.ui.inputName.trim().isEmpty()){
                    gp.player.setFarmName(gp.ui.inputName);
                    gp.ui.inputName = "";
                    gp.ui.titleScreenState = 3;
                    gp.ui.errorMessage = "";
                } else {
                    gp.ui.errorMessage = "Farm Name cannot be empty!";
                    gp.ui.errorMessageTime = System.currentTimeMillis();
                }
            } else if(code == KeyEvent.VK_BACK_SPACE){
                if(gp.ui.inputName.length() > 0){
                    gp.ui.inputName = gp.ui.inputName.substring(0, gp.ui.inputName.length()-1);
                }
            } else {
                char c = e.getKeyChar();
                if((Character.isLetterOrDigit(c) || c==' ')&& gp.ui.inputName.length() < 12){
                    gp.ui.inputName += c;
                }
            }
        }
        else if(gp.ui.titleScreenState == 3){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState; // atau state lain
            } 
        }
    }

    public void pauseState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
    }

    public void mapSelectState(int code){
        int totalMaps = gp.ui.mapList.size();
            int mapsPerRow = 5;

            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                if (gp.ui.mapSelectionNum < totalMaps) {
                    if (gp.ui.mapSelectionNum >= mapsPerRow) {
                        gp.ui.mapSelectionNum -= mapsPerRow;
                    } else {
                        gp.ui.mapSelectionNum = totalMaps;
                    }
                } else {
                    gp.ui.mapSelectionNum = totalMaps - 1;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                if (gp.ui.mapSelectionNum < totalMaps) {
                    if (gp.ui.mapSelectionNum < mapsPerRow) {
                        if (gp.ui.mapSelectionNum + mapsPerRow < totalMaps) {
                            gp.ui.mapSelectionNum += mapsPerRow;
                        } else {
                            gp.ui.mapSelectionNum = totalMaps;
                        }
                    } else {
                        gp.ui.mapSelectionNum = totalMaps;
                    }
                } else {
                    gp.ui.mapSelectionNum = 0;
                }
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                if (gp.ui.mapSelectionNum > 0 && gp.ui.mapSelectionNum <= totalMaps){
                    gp.ui.mapSelectionNum--;
                } else if (gp.ui.mapSelectionNum == 0) {
                        gp.ui.mapSelectionNum = totalMaps;
                } else if (gp.ui.mapSelectionNum == totalMaps) {
                    gp.ui.mapSelectionNum = totalMaps - 1;
                }
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                if (gp.ui.mapSelectionNum < totalMaps){
                    gp.ui.mapSelectionNum++;
                } else if (gp.ui.mapSelectionNum == totalMaps) {
                    gp.ui.mapSelectionNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.mapSelectionNum < totalMaps){
                    System.out.println("Map dipilih: " + gp.ui.mapList.get(gp.ui.mapSelectionNum).name);
                    if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Ocean")) {
                        gp.tileM = gp.oceanMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Forest River")) {
                        gp.tileM = gp.forestMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Mountain Lake")) {
                        gp.tileM = gp.mountainMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Farm")) {
                        gp.tileM = gp.farmMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Store")) {
                        gp.tileM = gp.storeMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Abigail's House")) {
                        gp.tileM = gp.abigailMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Caroline's House")) {
                        gp.tileM = gp.carolineMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Dasco's House")) {
                        gp.tileM = gp.dascoMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Mayor Tadi's House")) {
                        gp.tileM = gp.mayorMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Perry's House")) {
                        gp.tileM = gp.perryMap;
                    }
                    gp.player.worldX = gp.tileM.worldX * gp.tileSize;
                    gp.player.worldY = gp.tileM.worldY * gp.tileSize;
                    gp.gameState = gp.playState;
                    gp.player.consumeEnergy(10);
                    for(int i = 0; i<3;i++){
                        gp.time.addFiveMinutes();
                    }
                } else if (gp.ui.mapSelectionNum == totalMaps){
                    gp.gameState = gp.playState;
                }
            }
    }
    public void insufficientResourcesCookingState(int code) {
        if(gp.gameState == gp.insufficientResourcesCookingState) {
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.cookingState; // Kembali ke play state
            }
        }
    }
    public void shippingBinState(int code) {
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
            }
        }
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.selectItems != null) {
                if(gp.ui.selectItems instanceof Equipment){
                    //Gak ngapangapain
                }
                else{
                    gp.player.shippingBin.addItem(gp.ui.selectItems);
                    gp.player.inventory.removeItem(gp.ui.selectItems);
                    gp.ui.selectItems = null; // Reset selected item after shipping
                }
            }
        }
    }

    public void dialogueState(int code) {
        int maxCommandNum = gp.ui.dialogueOptions.length - 1; // atau dialogueOptions.length
        if(gp.tileM.currMap.equals("Store")) {
            maxCommandNum = gp.ui.dialogueOptionsStore.length - 1; // Atur sesuai jumlah opsi di toko
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if((gp.ui.dialogueCommandNum + 1) % 3 != 0 && gp.ui.dialogueCommandNum < maxCommandNum){
                gp.ui.dialogueCommandNum++;
            }
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if(gp.ui.dialogueCommandNum % 3 != 0){
                gp.ui.dialogueCommandNum--;
            }
        }
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            if(gp.ui.dialogueCommandNum - 3 >= 0){
                gp.ui.dialogueCommandNum -= 3;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            if(gp.ui.dialogueCommandNum + 3 <= maxCommandNum){
                gp.ui.dialogueCommandNum += 3;
            }
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
            gp.ui.dialogueCommandNum = 0;
            gp.ui.isAction = false;
        }
        if(code == KeyEvent.VK_ENTER){
            gp.ui.initInteract = false;
            if(gp.ui.dialogueCommandNum==0){
                try {
                    gp.npcManager.getActiveNPC().chat();
                } catch (WrongUseFunctionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                gp.ui.currentDialogue = gp.npcManager.getActiveNPC().getResponse();
                gp.ui.dialogueCommandNum = 5;
            }
            else if(gp.ui.dialogueCommandNum==1){
                //gift
            }
            else if(gp.ui.dialogueCommandNum==2){
                gp.npcManager.getActiveNPC().propose();
                gp.ui.currentDialogue = gp.npcManager.getActiveNPC().getResponse();
                gp.ui.dialogueCommandNum = 5;
            }
            else if(gp.ui.dialogueCommandNum==3){
                gp.npcManager.getActiveNPC().marry();
                gp.ui.currentDialogue = gp.npcManager.getActiveNPC().getResponse();
                gp.ui.dialogueCommandNum = 5;
            }
            else if(gp.ui.dialogueCommandNum==4){
                //store
            }
            else{
                return;
            }
        }
    }

    public void fishingState(int code, KeyEvent e) {
        if(gp.ui.isGuessing){
            if (code == KeyEvent.VK_ENTER) {
                int guess = Integer.parseInt(gp.ui.inputBuffer);
                if (gp.guessList.size() < gp.fishing.attempt - 1) {
                    gp.guessList.add(guess);
                }
                else{
                    gp.ui.isGuessing = false;
                }
                gp.ui.inputBuffer = ""; // Clear input after each attempt
            } else if (code == KeyEvent.VK_BACK_SPACE && gp.ui.inputBuffer.length() > 0) {
                gp.ui.inputBuffer = gp.ui.inputBuffer.substring(0, gp.ui.inputBuffer.length() - 1);
            } else if (e != null && Character.isDigit(e.getKeyChar())) { // For numbers only
                gp.ui.inputBuffer += e.getKeyChar();
            }
        }
        else{
            gp.fishing.start(
                gp.environmentStatus.season, 
                gp.environmentStatus.weather, 
                gp.tileM.currMap, 
                gp.time.getHour(), 
                gp.player.inventory, 
                gp.guessList
            );
            gp.ui.fishGuess = gp.fishing.guess;
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
                gp.guessList.clear();
                gp.gameState = gp.playState;
                }
        }
    }
    public void insufficientEnergyState(int code) {
        if(gp.gameState == gp.insufficientEnergyState) {
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState; // Kembali ke play state
            }
        }
    }
    public void stoveState(int code){
        if(gp.gameState == gp.stoveState){
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState; // Kembali ke play state
            }
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                if(gp.ui.cookingCommand > 0){
                    gp.ui.cookingCommand--;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                if(gp.ui.cookingCommand < 1){
                    gp.ui.cookingCommand++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                switch (gp.ui.cookingCommand) {
                    case 0: // Memasak
                        gp.gameState = gp.cookingState;
                        break;
                    case 1: // Tambah Bahan Bakar
                        gp.gameState = gp.addFuelState; // Ganti ke state menambah bahan bakar
                        break;
                }
            }
        }
    }

    public void addFuelState(int code) {
        if(gp.gameState == gp.addFuelState) {
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                if(gp.ui.slotCol != 0){
                    gp.ui.slotCol--;
                }
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                if(gp.ui.slotCol != 4){
                    gp.ui.slotCol++;
                }
            }
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                if(gp.ui.slotRow != 0){
                    gp.ui.slotRow--;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                if(gp.ui.slotRow != 3){
                    gp.ui.slotRow++;
                }
            }
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.selectItems != null) {
                    if(gp.ui.selectItems.getName().equals("Firewood")){
                        gp.stoveFuel += 1;
                        gp.player.inventory.removeItem(gp.ui.selectItems);
                    }
                    if(gp.ui.selectItems.getName().equals("Coal")){
                        gp.stoveFuel += 2;
                        gp.player.inventory.removeItem(gp.ui.selectItems);
                    }
                }
            }
        }
    }
}