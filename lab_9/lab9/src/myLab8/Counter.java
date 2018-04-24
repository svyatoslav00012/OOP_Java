package myLab8;

import container.Container;
import container.MyContainer;

public interface Counter {

	void readData(MyContainer c);

	void showData(MyContainer c);

	void count(MyContainer c);

	void showResults();

	void end();

	void debug(MyContainer c);
}
