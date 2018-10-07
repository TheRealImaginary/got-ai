package com.ali.me.view;

import com.ali.me.action.Action;
import com.ali.me.action.impl.PlaceholderAction;
import com.ali.me.state.State;
import com.ali.me.state.impl.TheStateThatKnowsNothing;
import com.ali.me.state.impl.TheStateThatKnowsNothing.NorthOfTheWall;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SolutionViewer extends JFrame {

    private class Controls extends JPanel {
        private JLabel pathCostLabel;
        private JLabel actionTakenLabel;
        private JLabel dragonGlassesLabel;
        private JLabel heuristicCostLabel;

        private JButton step;
        private JButton undo;
        private JButton timerControl;

        private Controls() {
            this.pathCostLabel = new JLabel("Path Cost: 0 | ");
            this.dragonGlassesLabel = new JLabel("Dragon Glasses: 0 | ");
            this.actionTakenLabel = new JLabel("Action Taken: None | ");
            this.heuristicCostLabel = new JLabel("Heusitic Cost: 0");

            this.step = new JButton("Step");
            this.undo = new JButton("Undo");
            this.timerControl = new JButton("Pause");

            this.add(this.pathCostLabel);
            this.add(this.dragonGlassesLabel);
            this.add(this.actionTakenLabel);
            this.add(this.heuristicCostLabel);

            this.add(this.step);
            this.add(this.undo);
            this.add(timerControl);
        }

        private void setPathCost(int pathCost) {
            this.pathCostLabel.setText("Path Cost: " + pathCost + " | ");
        }

        private void setDragonGlasses(int dragonGlasses) {
            this.dragonGlassesLabel.setText("Dragon Glasses: " + dragonGlasses + " | ");
        }

        private void setActionTaken(Action action) {
            String actionTaken = null;
            if (action == null) actionTaken = "None";
            else actionTaken = action.getActionString();
            this.actionTakenLabel.setText("Action Taken: " + actionTaken + " | ");
        }

        private void setHeuristicCost(int heuriticCost) {
            this.heuristicCostLabel.setText("Heuristic Cost: " + heuriticCost);
        }
    }

    private class MainView extends JPanel {
        private final ImageIcon JON_ICON = new ImageIcon("resources/jon_snow.jpeg");
        private final ImageIcon WHITE_WALKER_ICON = new ImageIcon("resources/white_walker.jpeg");
        private final ImageIcon DRAGON_STONE_ICON = new ImageIcon("resources/throne.jpeg");
        private final ImageIcon OBSTACLE_ICON = new ImageIcon("resources/obstacle.png");

        private NorthOfTheWall[][] initialGrid;
        private JButton[][] grid;
        private int row, col, numberOfRows, numberOfColumns;

        private MainView(State state) {
            this.initialGrid = ((TheStateThatKnowsNothing) state).getGrid();
            this.numberOfRows = this.initialGrid.length;
            this.numberOfColumns = this.initialGrid[0].length;

            this.setLayout(new GridLayout(numberOfRows, numberOfColumns));

            grid = new JButton[numberOfRows][numberOfColumns];
            for (int row = 0; row < numberOfRows; row++)
                for (int col = 0; col < numberOfColumns; col++) {
                    ImageIcon imageIcon = null;
                    switch (this.initialGrid[row][col]) {
                        case W:
                            imageIcon = WHITE_WALKER_ICON;
                            break;
                        case J:
                            imageIcon = JON_ICON;
                            this.row = row;
                            this.col = col;
                            break;
                        case D:
                            imageIcon = DRAGON_STONE_ICON;
                            break;
                        case O:
                            imageIcon = OBSTACLE_ICON;
                            break;
                    }
                    grid[row][col] = new JButton(imageIcon);
                    grid[row][col].setContentAreaFilled(false);
                    grid[row][col].setBorderPainted(false);
                    this.add(grid[row][col]);
                }
        }

        private void moveLeft() {
            if (this.grid[this.row][this.col].getIcon() == JON_ICON)
                this.grid[this.row][this.col].setIcon(null);
            this.col--;
            if (this.grid[this.row][this.col].getIcon() == null)
                this.grid[this.row][this.col].setIcon(JON_ICON);
        }

        private void moveRight() {
            if (this.grid[this.row][this.col].getIcon() == JON_ICON)
                this.grid[this.row][this.col].setIcon(null);
            this.col++;
            if (this.grid[this.row][this.col].getIcon() == null)
                this.grid[this.row][this.col].setIcon(JON_ICON);
        }

        private void moveUp() {
            if (this.grid[this.row][this.col].getIcon() == JON_ICON)
                this.grid[this.row][this.col].setIcon(null);
            this.row--;
            if (this.grid[this.row][this.col].getIcon() == null)
                this.grid[this.row][this.col].setIcon(JON_ICON);
        }

        private void moveDown() {
            if (this.grid[this.row][this.col].getIcon() == JON_ICON)
                this.grid[this.row][this.col].setIcon(null);
            this.row++;
            if (this.grid[this.row][this.col].getIcon() == null)
                this.grid[this.row][this.col].setIcon(JON_ICON);
        }

        private void attack(boolean revive) {
            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};
            for (int i = 0; i < dx.length; i++) {
                int r = row + dx[i], c = col + dy[i];
                if (r >= 0 && r < this.numberOfRows && c >= 0 && c < this.numberOfColumns && this.initialGrid[r][c] == NorthOfTheWall.W)
                    if (!revive) this.grid[r][c].setIcon(null);
                    else this.grid[r][c].setIcon(WHITE_WALKER_ICON);
            }
        }

        private void pickup() {
        }
    }

    private int currentStep;
    private List<State> path;
    private Controls controls;
    private MainView mainView;
    private Timer timer;

    public SolutionViewer(String title, List<State> path) throws HeadlessException {
        super(title);
        this.currentStep = 0;
        this.path = path;

        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.controls = new Controls();
        this.mainView = new MainView(this.path.get(0));

        this.add(this.mainView, BorderLayout.CENTER);
        this.add(this.controls, BorderLayout.NORTH);

        this.controls.step.addActionListener((actionEvent) -> this.step());
        this.controls.undo.addActionListener((actionEvent) -> {
            if (currentStep == 0) return;
            State state = this.path.get(currentStep--);
            switch (state.getAction().getAction()) {
                case PlaceholderAction.MOVE_LEFT:
                    this.mainView.moveRight();
                    break;
                case PlaceholderAction.MOVE_RIGHT:
                    this.mainView.moveLeft();
                    break;
                case PlaceholderAction.MOVE_UP:
                    this.mainView.moveDown();
                    break;
                case PlaceholderAction.MOVE_DOWN:
                    this.mainView.moveUp();
                    break;
                case PlaceholderAction.ATTACK:
                    this.mainView.attack(true);
                    break;
                case PlaceholderAction.PICK_UP:
                    this.mainView.pickup();
                    break;
            }
            State previousState = this.path.get(currentStep);
            int dragonGlasses = ((TheStateThatKnowsNothing) previousState).getDragonGlasses();
            this.controls.setPathCost(previousState.getPathCost());
            this.controls.setActionTaken(previousState.getAction());
            this.controls.setDragonGlasses(dragonGlasses);
            this.controls.setHeuristicCost(previousState.getHeuristicCost());
        });
        this.controls.timerControl.addActionListener((actionEvent) -> {
            JButton source = (JButton) actionEvent.getSource();
            String action = source.getText();
            if (action.equals("Pause")) {
                this.timer.stop();
                source.setText("Play");
            } else {
                this.timer.start();
                source.setText("Pause");
            }
        });
        this.timer = new Timer(800, (actionEvent) -> this.step());
        this.timer.start();
        this.setVisible(true);
    }

    private void step() {
        if (this.currentStep + 1 == this.path.size()) return;
        State nextState = this.path.get(++this.currentStep);
        int dragonGlasses = ((TheStateThatKnowsNothing) nextState).getDragonGlasses();
        switch (nextState.getAction().getAction()) {
            case PlaceholderAction.MOVE_LEFT:
                this.mainView.moveLeft();
                break;
            case PlaceholderAction.MOVE_RIGHT:
                this.mainView.moveRight();
                break;
            case PlaceholderAction.MOVE_UP:
                this.mainView.moveUp();
                break;
            case PlaceholderAction.MOVE_DOWN:
                this.mainView.moveDown();
                break;
            case PlaceholderAction.ATTACK:
                this.mainView.attack(false);
                break;
            case PlaceholderAction.PICK_UP:
                this.mainView.pickup();
                break;
        }
        this.controls.setPathCost(nextState.getPathCost());
        this.controls.setActionTaken(nextState.getAction());
        this.controls.setDragonGlasses(dragonGlasses);
        this.controls.setHeuristicCost(nextState.getHeuristicCost());
    }
}
