package com.mycompany.a3;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {
	private ArrayList<GameObject> myGameObjects = new ArrayList<GameObject>();

	@Override
	public void add(GameObject object) {
		// TODO Auto-generated method stub
		myGameObjects.add(object);
	}

	@Override
	public IIterator getIterator() {
		// TODO Auto-generated method stub
		return new GameObjectIt();
	} 
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		myGameObjects.clear();
	}
	
	private class GameObjectIt implements IIterator {
		private int currElementIndex;
		
		public GameObjectIt() {
			currElementIndex = -1;
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (myGameObjects.size ( ) <= 0)
				return false;
			if (currElementIndex == myGameObjects.size() - 1 )
                return false;
             return true;
		}

		@Override
		public GameObject getNext() {
			// TODO Auto-generated method stub
			GameObject gameObject = null;
			if ((hasNext())) {
				currElementIndex ++ ;
				gameObject = myGameObjects.get(currElementIndex);
			}
			return gameObject;
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			currElementIndex = -1;
		}

	}

	
}
