package thd.game.utilities;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;


/**
 * Used for game objects that have to be sorted by the distance to the background.
 */
public class SortedGameObjectsList extends LinkedList<GameObject> {
    @Override
    public boolean add(GameObject toAdd) {
        int indexToSortIn = 0;
        for (GameObject gameObject : this) {
            if (gameObject.getDistanceToBackground() >= toAdd.getDistanceToBackground()) {
                break;
            }
            indexToSortIn++;
        }
        add(indexToSortIn, toAdd);
        return true;
    }
}
