//public class GameWorldManagerWorldString {
//private void spawnGameObjectsFromWorldString() {
//        String[] lines = world.split("\\R");
//        int scale = 100;
//        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
//        String line = lines[lineIndex];
//        for (int columnIndex = 0; columnIndex < line.length(); columnIndex++) {
//        spawnGameObjectsByWorldStringPostion(line, columnIndex, lineIndex, scale);
//        }
//        }
//        }
//
//private void spawnGameObjectsByWorldStringPostion(String line, int columnIndex, int lineIndex, int scale) {
//        char currentChar = line.charAt(columnIndex);
//        if (currentChar == ' ') {
//        return;
//        }
//        Position spawnLocation = calculateSpawnLocation(lineIndex, columnIndex, scale);
//        if (currentChar == 'S') {
//        addActivatableGameObject(new Star(gameView, this, spawnLocation));
//        } else if (currentChar == 'B') {
//        spawnGameObject(new Mountains(gameView, this, spawnLocation));
//        }
//        }
//
//private Position calculateSpawnLocation(int line, int column, int scale) {
//        return new Position((column - worldOffsetColumns) * scale, (line - worldOffsetLines) * scale);
//        }
//}
