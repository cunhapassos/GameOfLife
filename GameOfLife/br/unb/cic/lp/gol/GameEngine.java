package br.unb.cic.lp.gol;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import org.reflections.*;

import br.unb.cic.lp.gol.strategies.DefaultStrategy;
import br.unb.cic.lp.gol.strategies.Strategy;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * Essa implementacao eh nao inifinita, ou seja, nem todas as celulas possuem
 * oito celulas vizinhas. Por exemplo, a celula de coordenada (0,0) possui
 * apenas tres celulas vizinhas, (0,1), (1,0) e (1,1).
 * 
 * Um ambiente eh representado como um array bidimensional de celulas, com
 * altura (height) e comprimento (width).
 * 
 * @author rbonifacio
 */
public class GameEngine extends Observable {
	/**
	 * @uml.property name="height"
	 */
	private int height;
	/**
	 * @uml.property name="width"
	 */
	private int width;
	/**
	 * @uml.property name="cells" multiplicity="(0 -1)" dimension="2"
	 */
	private Cell[][] cells;
	private List<Strategy> availableStrategies;
	private Strategy currentStrategy;

	// private Statistics statistics;

	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimentsao horizontal do ambiente
	 */
	public GameEngine(int height, int width) {
		// retirei
		// ", Statistics statistics" dos
		// argumentos
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}

		try {
			loadAvailableStrategies();
		} catch (Exception e) {
			System.out.println("Nao foi possivel carregar as estrategias :(");
		}
		
		loadDefaultStrategy();

		// this.statistics = statistics;
	}

	private void loadAvailableStrategies() throws Exception {

		this.availableStrategies = new ArrayList<Strategy>();

		Reflections reflections = new Reflections(
				"br.unb.cic.lp.gol.strategies");

		Set<Class<? extends Strategy>> plugedStrategies = reflections
				.getSubTypesOf(Strategy.class);

		Iterator<Class<? extends Strategy>> iterator = plugedStrategies
				.iterator();
		
		while (iterator.hasNext()) {
			Class<? extends Strategy> cl = iterator.next();
			if (!Modifier.isAbstract(cl.getModifiers()))		// instancia apenas se nao abstrata
				this.availableStrategies.add(cl.newInstance());
		}
		
		for (Strategy strategy : availableStrategies)	// decidi inicializar todas as instancias
			strategy.init(this, this.cells);			// de estrategias disponiveis logo
	}

	private void loadDefaultStrategy() {
		Annotation[] annotations;
		
		for (Strategy strategy : this.availableStrategies) {	// dentre as estrategias disponiveis, deve haver uma anotada @Default
			annotations = strategy.getClass().getAnnotations();
		
			for (Annotation annotation : annotations) {
				if (annotation instanceof DefaultStrategy) {
					this.currentStrategy = strategy;
					return;							// retorna na primeira que achar, que idealmente deve ser unica
				}
			}
		}
		
		// Se der tempo, criar uma excessao para ser lancada caso chegue aqui...
	}
		
	public List<String> getAvailableStrategyNames() {

		List<String> strategyNames = new ArrayList<String>();
		for (Strategy strategy : this.availableStrategies)
			strategyNames.add(strategy.getName());

		Collections.sort(strategyNames);

		return strategyNames;

	}

	public String getCurrentStrategyName() {
		return this.currentStrategy.getName();
	}
	
	public String getStrategyDescriptionByName(String name) {
		for (Strategy strategy : availableStrategies)
			if (strategy.getName().equals(name))
				return strategy.getRule() + " (" + strategy.getCharacter() + ")";
		
		return "";
	}

	public void setCurrentStrategyByName(String name) {
		for (Strategy strategy : this.availableStrategies)
			if (strategy.getName().equals(name))
				this.currentStrategy = strategy;
	}
	
	
	
	public void nextGeneration() {
		currentStrategy.nextGeneration(height, width);
	}

	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i
	 *            posicao vertical da celula
	 * @param j
	 *            posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException
	 *             caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if (validPosition(i, j)) {
			cells[i][j].revive();
			setChanged(); // indica que o estado do objeto mudou.
			notifyObservers(ObserverMessages.CELL_REVIVE.getMessage());// inserido p adequar ao padrao
											// observer
		} else {
			new InvalidParameterException("Invalid position (" + i + ", " + j
					+ ")");
		}
	}

	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i
	 *            Posicao vertical da celula
	 * @param j
	 *            Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException
	 *             caso a posicao (i,j) nao seja valida.
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if (validPosition(i, j)) {
			return cells[i][j].isAlive();
		} else {
			throw new InvalidParameterException("Invalid position (" + i + ", "
					+ j + ")");
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. Esse metodo eh
	 * particularmente util para o calculo de estatisticas e para melhorar a
	 * testabilidade.
	 * 
	 * @return numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (isCellAlive(i, j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	public boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}

	/* Metodos de acesso as propriedades height e width */

	/**
	 * @return
	 * @uml.property name="height"
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 * @uml.property name="height"
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return
	 * @uml.property name="width"
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 * @uml.property name="width"
	 */
	public void setWidth(int width) {
		this.width = width;
	}
}
