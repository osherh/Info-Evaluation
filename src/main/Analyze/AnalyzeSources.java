package main.Analyze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import edu.stanford.nlp.util.StringUtils;
import main.database.DataList;
import main.database.InteractiveTableTuple;
import main.database.TableTuple;

/**
 *
 * @author netanel this class contains all the sources to be analyzed. It uses
 *         the AnalyzeParagraph class in order to analyze each source, and the
 *         merge all the lists of each source so we have one big list with all
 *         the information we were looking for ready to be delivered to the GUI
 *
 *
 *
 */

public class AnalyzeSources {
	private final List<String> sources;
	private int numOfSources;
	private final DataList data;
	private Map<String, Integer> words;
	private List<String> nonKeyWords;
	private List<InteractiveTableTuple> interactiveData;

	public AnalyzeSources() {
		sources = new ArrayList<>();
		data = new DataList();
		words = new HashMap<String, Integer>();
		initialNonKey();
		interactiveData = new ArrayList<>();
	}

	private void initialNonKey() {
		nonKeyWords = new ArrayList<>();
		nonKeyWords.add("of");
		nonKeyWords.add("on");
		nonKeyWords.add("in");
		nonKeyWords.add("at");
		nonKeyWords.add("while");
		nonKeyWords.add("under");
	}

	private void findKeyWords() {
		words = new HashMap<String, Integer>();
		for (TableTuple i : this.data)
			for (String str : StringUtils.split(i.getReason())) {
				if (this.nonKeyWords.contains(str))
					continue;
				if (!this.words.containsKey(str))
					this.words.put(str, 1);
				else {
					int value = this.words.get(str);
					this.words.replace(str, ++value);
				}
			}

		for (TableTuple i : this.data)
			for (String ¢ : StringUtils.split(i.getReason())) {
				if (this.nonKeyWords.contains(¢))
					continue;
				if (this.words.get(¢) >= 2)
					i.addKeyWord(¢);
			}
	}

	/**
	 * When we add a source, we immediately get the dataList of it and add it to
	 * our big list of info
	 * 
	 * @param src
	 */
	public void addSource(final String src) {
		sources.add(src);
		++numOfSources;
		data.merge(new AnalyzePage(src).getDetails());
		interactiveData.addAll(new AnalyzePage(src).getInteractiveDetails());
		findKeyWords();
	}

	public void addSourceFile(String pathToFile) throws FileNotFoundException {
		try {
			addSource(Files.toString(new File(pathToFile), Charsets.UTF_8));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException ¢) {
			¢.printStackTrace();
		}
	}
	
	public void addSourceFile(String pathToFile, String year) throws FileNotFoundException {
		try {
			addSource(Files.toString(new File(pathToFile), Charsets.UTF_8),year);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException ¢) {
			¢.printStackTrace();
		}
	}

	public void addSource(final String src, String year) {
		sources.add(src);
		++numOfSources;
		data.merge(new AnalyzePage(src, year).getDetails());
		interactiveData.addAll(new AnalyzePage(src).getInteractiveDetails());
		findKeyWords();
	}

	public int getNumOfSources() {
		return numOfSources;
	}

	public DataList getData() {
		return data;
	}

	public Map<String, Integer> getWords() {
		return words;
	}

	public List<InteractiveTableTuple> getInteractiveData() {
		return this.interactiveData;
	}
}