package ui;

import audio.AudioController;
import audio.Sounds;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Client;
import utils.enums.RenderingMode;
import utils.enums.ScreenResolution;

/**
 * @author Adam Kona Class which handles the creation and functionality of components in the main
 *     menu.
 */
public class MenuController {

  private AudioController audioController;
  private Client client;

  private boolean viewSettings = false;
  private boolean soundFX = true;
  private boolean music = true;
  private Stage primaryStage;
  private Stack<ArrayList<Node>> backTree = new Stack<>();
  private ArrayList<Node> itemsOnScreen = new ArrayList<>();

  private Button singlePlayerBtn;
  private Button multiplayerBtn;
  private Button startGameBtn;
  private Button backBtn;
  private Button playBtn;
  private Button musicBtn;
  private Button soundFxBtn;
  private Button creditsBtn;
  private Button settingsBtn;
  private Button quitBtn;
  private Button createGameBtn;
  private Button joinGameBtn;
  private Button createLobbyBtn;
  private Button incrVolumeBtn;
  private Button decrVolumeBtn;
  private Button startMGameBtn;
  private Button lowRes;
  private Button medRes;
  private Button highRes;

  private ImageView volumeImg;
  private ImageView lowResImageView;
  private ImageView medResImageView;
  private ImageView highResImageView;
  private ImageView playView;
  private ImageView logo;
  private ImageView starting;
  private ImageView startingM;
  private ImageView singlePlayerImageView;
  private ImageView multiplayerImageView;
  private ImageView musicOnView;
  private ImageView fxView;
  private ImageView incrView;
  private ImageView decrView;
  private ImageView creditsView;
  private ImageView quitView;
  private ImageView joinGameView;
  private ImageView createGameView;
  private ImageView createLobbyView;
  private ImageView continueView;
  private ImageView settingsView;
  private ImageView backImageView;

  private Image highResW;
  private Image highResG;
  private Image medResW;
  private Image medResG;
  private Image lowResW;
  private Image lowResG;

  private Label lobbyStatusLbl;
  private Label loadingDots;
  private Label playersInLobby;

  private TextField nameEntry;
  private Button nameEntryBtn;
  private ImageView nameEntryImg;

  private VBox multiplayerOptions;
  private VBox gameModeOptions;
  private VBox resolutionOptions;
  private VBox nameEntryOptions;
  private VBox searchingForMutiplayers;
  private Font font;

  private List<ImageView> imageViews;
  private List<Double> originalViewWidths;
  private List<Double> minimumViewWidths;

  private boolean isHome = true;

  /**
   * @param audio Global audio controller which is passed around the system
   * @param stage The game window
   * @author Adam Kona Constructor takes in the audio controller stage and game scene
   */
  public MenuController(AudioController audio, Stage stage, Client client) {
    this.audioController = audio;
    this.primaryStage = stage;
    this.client = client;
    originalViewWidths = new ArrayList<>();
    minimumViewWidths = new ArrayList<>();
  }

  /**
   * Hides the components on the screen
   */
  private void hideItemsOnScreen() {
    for (Node item : itemsOnScreen) {
      item.setVisible(false);
    }
  }

  /**
   * @author Adam Kona Hides the items currently on the screen and moves them onto the stack which
   *     will store which components were previously showing
   */
  private void moveItemsToBackTree() {
    hideItemsOnScreen();
    ArrayList<Node> components = new ArrayList<>(itemsOnScreen);
    backTree.push(components);
    itemsOnScreen.clear();
  }

  /** @author Adam Kona Shows items on the screen which have been previously set to hidden. */
  private void showItemsOnScreen() {
    for (Node item : itemsOnScreen) {
      item.setVisible(true);
    }
  }

  /**
   * @param s The screen resolution we want to update the game to.
   * @author Adam Kona Handles the changing of images corresponding to the different resolutions
   *     available in the game.
   */
  private void updateView(ScreenResolution s) {
    switch (s) {
      case LOW:
        lowResImageView.setImage(lowResW);
        medResImageView.setImage(medResG);
        highResImageView.setImage(highResG);

        break;
      case MEDIUM:
        lowResImageView.setImage(lowResG);
        medResImageView.setImage(medResW);
        highResImageView.setImage(highResG);
        break;
      case HIGH:
        lowResImageView.setImage(lowResG);
        medResImageView.setImage(medResG);
        highResImageView.setImage(highResW);
        break;
    }
    client.updateResolution(s);
  }

  /**
   * Creates all the menu items and defines their functionality.
   *
   * @return The node containing the menu which will be returned to the game main window.
   */
  public Node createMainMenu() {

    try {
      this.font =
          Font.loadFont(
              new FileInputStream(new File("src/main/resources/ui/PressStart2P.ttf")), 26);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    StackPane root = new StackPane();
    root.setPrefSize(1920, 1080);

    ImageView bg = new ImageView("sprites/default/backgrounds/default.png");

    bg.fitWidthProperty().bind(this.primaryStage.widthProperty());
    root.getChildren().add(bg);
    StackPane.setAlignment(bg, Pos.CENTER);

    logo = new ImageView("ui/MIPS-B.png");
    logo.preserveRatioProperty();
    StackPane.setAlignment(logo, Pos.TOP_CENTER);
    StackPane.setMargin(logo, new Insets(200, 0, 0, 0));
    logo.setPreserveRatio(true);
    root.getChildren().add(logo);
    logo.setVisible(true);

    startGameBtn = new Button();
    StackPane.setAlignment(startGameBtn, Pos.CENTER);
    StackPane.setMargin(startGameBtn, new Insets(160, 0, 0, 0));
    Image startImg = new Image("ui/start.png");
    starting = new ImageView(startImg);
    startGameBtn.setGraphic(starting);
    startGameBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(startGameBtn);
    startGameBtn.setVisible(false);
    startGameBtn.setOnAction(
        e -> {
          audioController.playSound(Sounds.click);
          client.startSinglePlayerGame();
        });

    this.singlePlayerBtn = new Button();
    Image singleplayerImg = new Image("ui/Single-Player.png");
    singlePlayerImageView = new ImageView(singleplayerImg);
    this.singlePlayerBtn.setGraphic(singlePlayerImageView);
    this.singlePlayerBtn.setStyle("-fx-background-color: transparent;");
    this.singlePlayerBtn.setOnAction(
        e -> {
          audioController.playSound(Sounds.click);
          moveItemsToBackTree();
          itemsOnScreen.add(startGameBtn);
          startGameBtn.setVisible(true);
        });

    root.getChildren().add(this.singlePlayerBtn);
    this.singlePlayerBtn.setVisible(true);

    this.multiplayerBtn = new Button();
    Image multiplayerImg = new Image("ui/Multiplayer.png");
    multiplayerImageView = new ImageView(multiplayerImg);
    this.multiplayerBtn.setGraphic(multiplayerImageView);
    this.multiplayerBtn.setStyle("-fx-background-color: transparent;");
    this.multiplayerBtn.setOnAction(
        e -> {
          audioController.playSound(Sounds.click);
          moveItemsToBackTree();
          itemsOnScreen.add(nameEntryOptions);
          showItemsOnScreen();
        });
    root.getChildren().add(this.multiplayerBtn);
    this.multiplayerBtn.setVisible(true);

    lowRes = new Button();
    lowRes.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(lowRes);
    lowResW = new Image("ui/1366x768-W.png");
    lowResG = new Image("ui/1366x768-G.png");
    lowResImageView = new ImageView(lowResG);
    lowResImageView.setPreserveRatio(true);
    lowResImageView.setFitWidth(350);
    lowRes.setGraphic(lowResImageView);
    lowRes.setVisible(true);
    lowRes.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          updateView(ScreenResolution.LOW);
        });

    medRes = new Button();
    medRes.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(medRes);
    medResW = new Image("ui/1920x1080-W.png");
    medResG = new Image("ui/1920x1080-G.png");
    medResImageView = new ImageView(medResG);
    medResImageView.setPreserveRatio(true);
    medResImageView.setFitWidth(350);
    medRes.setGraphic(medResImageView);
    medRes.setVisible(true);
    medRes.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          updateView(ScreenResolution.MEDIUM);
        });

    highRes = new Button();
    highRes.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(highRes);
    highResW = new Image("ui/2650x1440-W.png");
    highResG = new Image("ui/2650x1440-G.png");
    highResImageView = new ImageView(highResG);
    highResImageView.setPreserveRatio(true);
    highResImageView.setFitWidth(350);
    highRes.setGraphic(highResImageView);
    highRes.setVisible(true);
    highRes.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          updateView(ScreenResolution.HIGH);
        });

    resolutionOptions = new VBox(20, lowRes, medRes, highRes);
    resolutionOptions.setAlignment(Pos.CENTER_RIGHT);
    StackPane.setAlignment(resolutionOptions, Pos.CENTER_RIGHT);
    StackPane.setMargin(resolutionOptions, new Insets(100, 20, 0, 0));
    root.getChildren().add(resolutionOptions);

    resolutionOptions.setVisible(false);

    gameModeOptions = new VBox(10, singlePlayerBtn, multiplayerBtn);
    gameModeOptions.setAlignment(Pos.CENTER);
    StackPane.setAlignment(gameModeOptions, Pos.CENTER);
    StackPane.setMargin(gameModeOptions, new Insets(100, 0, 0, 0));
    root.getChildren().add(gameModeOptions);
    gameModeOptions.setVisible(false);

    playBtn = new Button();
    StackPane.setAlignment(playBtn, Pos.CENTER);
    StackPane.setMargin(playBtn, new Insets(160, 0, 0, 0));
    Image playImg = new Image("ui/play.png");
    playView = new ImageView(playImg);
    playBtn.setGraphic(playView);
    playBtn.setStyle("-fx-background-color: transparent;");
    playBtn.setOnAction(
        e -> {
          audioController.playSound(Sounds.click);
          isHome = false;
          backBtn.setVisible(true);
          moveItemsToBackTree();
          itemsOnScreen.add(gameModeOptions);
          showItemsOnScreen();
        });
    root.getChildren().add(playBtn);

    ToggleGroup renderingModeGroup = new ToggleGroup();
    RadioButton standardScalingBtn = new RadioButton("standard");
    initialiseRenderingButtons(
        standardScalingBtn, 0, 0, root, renderingModeGroup, RenderingMode.STANDARD_SCALING);
    RadioButton noScalingBtn = new RadioButton("no scaling");
    initialiseRenderingButtons(
        noScalingBtn, 0, 60, root, renderingModeGroup, RenderingMode.NO_SCALING);
    RadioButton integerScalingBtn = new RadioButton("integer");
    initialiseRenderingButtons(
        integerScalingBtn, 0, 120, root, renderingModeGroup, RenderingMode.INTEGER_SCALING);
    RadioButton smoothScalingBtn = new RadioButton("smooth");
    initialiseRenderingButtons(
        smoothScalingBtn, 0, 180, root, renderingModeGroup, RenderingMode.SMOOTH_SCALING);

    standardScalingBtn.setSelected(true);
    client.setRenderingMode(RenderingMode.STANDARD_SCALING);

    musicBtn = new Button();
    StackPane.setAlignment(musicBtn, Pos.CENTER_LEFT);
    StackPane.setMargin(musicBtn, new Insets(0, 0, 25, 0));
    musicBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(musicBtn);
    Image musicOn = new Image("ui/Music-On.png");
    Image musicOff = new Image("ui/Music-Off.png");
    musicOnView = new ImageView(musicOn);
    musicBtn.setGraphic(musicOnView);
    musicBtn.setVisible(false);
    musicBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          if (music) {
            musicBtn.setGraphic(new ImageView(musicOff));
            music = false;
            audioController.setMusicVolume(0);
          } else {
            musicBtn.setGraphic(new ImageView(musicOn));
            music = true;
            audioController.setMusicVolume(0.5);
          }
        });

    soundFxBtn = new Button();
    StackPane.setAlignment(soundFxBtn, Pos.CENTER_LEFT);
    StackPane.setMargin(soundFxBtn, new Insets(150, 0, 0, 0));
    soundFxBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(soundFxBtn);
    Image soundFXOn = new Image("ui/SoundFX-On.png");
    Image soundFXOff = new Image("ui/SoundFX-Off.png");
    fxView = new ImageView(soundFXOn);
    fxView.setFitWidth(500);
    fxView.setPreserveRatio(true);
    soundFxBtn.setGraphic(fxView);
    soundFxBtn.setVisible(false);
    soundFxBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          if (soundFX) {
            fxView.setImage(soundFXOff);
            soundFX = false;
            audioController.setSoundVolume(0);
          } else {
            fxView.setImage(soundFXOn);
            soundFX = true;
            audioController.setSoundVolume(0.5);
          }
        });

    volumeImg = new ImageView(new Image("ui/Volume.png"));
    StackPane.setAlignment(volumeImg, Pos.CENTER_LEFT);
    StackPane.setMargin(volumeImg, new Insets(400, 0, 0, 100));
    volumeImg.setVisible(false);
    volumeImg.setPreserveRatio(true);
    root.getChildren().add(volumeImg);
    volumeImg.setFitWidth(250);

    incrVolumeBtn = new Button();
    StackPane.setAlignment(incrVolumeBtn, Pos.CENTER_LEFT);
    StackPane.setMargin(incrVolumeBtn, new Insets(530, 0, 0, 250));
    incrVolumeBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(incrVolumeBtn);
    incrView = new ImageView(new Image("ui/increaseVolume.png"));
    incrView.setPreserveRatio(true);
    incrView.setFitWidth(50);
    incrVolumeBtn.setGraphic(incrView);
    incrVolumeBtn.setVisible(false);
    incrVolumeBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          audioController.increaseVolume();
        });

    decrVolumeBtn = new Button();
    StackPane.setAlignment(decrVolumeBtn, Pos.CENTER_LEFT);
    StackPane.setMargin(decrVolumeBtn, new Insets(530, 0, 0, 150));
    decrVolumeBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(decrVolumeBtn);
    decrView = new ImageView(new Image("ui/decreaseVolume.png"));
    decrView.setPreserveRatio(true);
    decrView.setFitWidth(50);
    decrVolumeBtn.setGraphic(decrView);
    decrVolumeBtn.setVisible(false);
    decrVolumeBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          audioController.decreaseVolume();
        });

    creditsBtn = new Button();
    StackPane.setAlignment(creditsBtn, Pos.BOTTOM_CENTER);
    StackPane.setMargin(creditsBtn, new Insets(0, 0, 50, 0));
    creditsBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(creditsBtn);
    Image creditsImg = new Image("ui/Credits.png");
    creditsView = new ImageView(creditsImg);
    creditsBtn.setGraphic(creditsView);
    creditsBtn.setVisible(false);

    joinGameBtn = new Button();
    joinGameBtn.setPickOnBounds(true);
    joinGameBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(joinGameBtn);
    Image joinGameImg = new Image("ui/join-game.png");
    joinGameView = new ImageView(joinGameImg);
    joinGameBtn.setGraphic(joinGameView);
    joinGameBtn.setVisible(true);
    joinGameBtn.setOnAction(
        event -> {
          client.joinMultiplayerLobby();
        });

    createGameBtn = new Button();
    createGameBtn.setPickOnBounds(true);
    Image createGameImg = new Image("ui/create-game.png");
    createGameBtn.setStyle("-fx-background-color: transparent;");
    createGameView = new ImageView(createGameImg);
    createGameBtn.setGraphic(createGameView);
    createGameBtn.setVisible(true);
    root.getChildren().add(createGameBtn);
    createGameBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          moveItemsToBackTree();
          itemsOnScreen.add(searchingForMutiplayers);
          itemsOnScreen.add(startMGameBtn);
          searchingForMutiplayers.setVisible(true);
          startMGameBtn.setVisible(true);
          client.createMultiplayerLobby();
        });

    multiplayerOptions = new VBox(10, createGameBtn, joinGameBtn);
    multiplayerOptions.setAlignment(Pos.CENTER);
    StackPane.setAlignment(multiplayerOptions, Pos.CENTER);
    StackPane.setMargin(multiplayerOptions, new Insets(100, 0, 0, 0));
    root.getChildren().add(multiplayerOptions);
    multiplayerOptions.setVisible(false);

    createLobbyBtn = new Button();
    StackPane.setAlignment(createGameBtn, Pos.BOTTOM_CENTER);
    StackPane.setMargin(createGameBtn, new Insets(0, 0, 300, 0));
    createLobbyBtn.setStyle("-fx-background-color: transparent;");
    Image createLobbyImg = new Image("ui/Create-Lobby.png");
    createLobbyView = new ImageView(createLobbyImg);
    createLobbyBtn.setGraphic(createLobbyView);
    createLobbyBtn.setVisible(false);
    root.getChildren().add(createLobbyBtn);

    lobbyStatusLbl = new Label("Searching for players");
    lobbyStatusLbl.setTextFill(Color.WHITE);
    lobbyStatusLbl.setFont(this.font);

    loadingDots = new Label(" .");
    loadingDots.setTextFill(Color.WHITE);
    loadingDots.setFont(this.font);

    playersInLobby = new Label("Players in lobby: 0");
    playersInLobby.setTextFill(Color.WHITE);
    playersInLobby.setFont(this.font);

    searchingForMutiplayers = new VBox(5, lobbyStatusLbl, loadingDots, playersInLobby);
    searchingForMutiplayers.setAlignment(Pos.CENTER);
    StackPane.setAlignment(searchingForMutiplayers, Pos.CENTER);
    root.getChildren().add(searchingForMutiplayers);
    searchingForMutiplayers.setVisible(false);

    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.ZERO,
                event -> {
                  String statusText = loadingDots.getText();
                  loadingDots.setText((" . . .".equals(statusText)) ? " ." : statusText + " .");
                }),
            new KeyFrame(Duration.millis(1000)));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    nameEntry = new TextField();
    Font nameEntryFont = Font.font("Verdana");
    try {
      nameEntryFont =
          Font.loadFont(
              new FileInputStream(new File("src/main/resources/ui/PressStart2P.ttf")), 16);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    nameEntry.setPromptText("Please enter your player name...");
    nameEntry.setStyle(
        "-fx-text-inner-color: white; "
            + "-fx-prompt-text-fill: white; "
            + "-fx-background-color: transparent;");
    nameEntry.setFont(nameEntryFont);
    nameEntry.setAlignment(Pos.CENTER);

    Line clear = new Line(0, 100, 600, 100);
    clear.setStroke(Color.WHITE);
    VBox nameAndLine = new VBox(nameEntry, clear);
    nameAndLine.setAlignment(Pos.CENTER);

    nameEntryBtn = new Button();
    nameEntryBtn.setStyle("-fx-background-color: transparent;");
    Image continueImg = new Image("ui/continue.png");
    continueView = new ImageView(continueImg);
    nameEntryBtn.setGraphic(continueView);
    nameEntryBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          moveItemsToBackTree();
          this.client.setName(nameEntry.getText());
          itemsOnScreen.add(multiplayerOptions);
          showItemsOnScreen();
        });

    nameEntryOptions = new VBox(30, nameAndLine, nameEntryBtn);
    nameEntryOptions.setAlignment(Pos.CENTER);
    StackPane.setAlignment(nameEntryOptions, Pos.CENTER);
    StackPane.setMargin(nameEntryOptions, new Insets(100, 250, 0, 250));
    nameEntryOptions.setPrefWidth(300);
    nameEntryOptions.setVisible(false);
    root.getChildren().add(nameEntryOptions);

    quitBtn = new Button();
    StackPane.setAlignment(quitBtn, Pos.TOP_RIGHT);
    StackPane.setMargin(quitBtn, new Insets(50, 50, 0, 0));
    quitBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(quitBtn);
    Image quitImg = new Image("ui/quit.png");
    quitView = new ImageView(quitImg);
    quitBtn.setGraphic(quitView);
    quitBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          System.exit(0);
        });

    GaussianBlur gaussianBlur = new GaussianBlur();
    gaussianBlur.setRadius(11);

    settingsBtn = new Button();
    settingsBtn.setStyle("-fx-background-color: transparent;");
    StackPane.setAlignment(settingsBtn, Pos.TOP_LEFT);
    StackPane.setMargin(settingsBtn, new Insets(50, 0, 0, 50));
    Image settingsImg = new Image("ui/settings.png");
    settingsView = new ImageView(settingsImg);
    settingsView.setFitHeight(50);
    settingsView.setFitWidth(50);
    settingsView.setPreserveRatio(true);
    settingsBtn.setGraphic(settingsView);
    settingsBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);
          if (!viewSettings) {
            musicBtn.setVisible(true);
            soundFxBtn.setVisible(true);
            creditsBtn.setVisible(true);
            volumeImg.setVisible(true);
            resolutionOptions.setVisible(true);
            viewSettings = true;
            incrVolumeBtn.setVisible(true);
            decrVolumeBtn.setVisible(true);
            bg.setEffect(gaussianBlur);
            hideItemsOnScreen();
            backBtn.setVisible(false);
            standardScalingBtn.setVisible(true);
            noScalingBtn.setVisible(true);
            integerScalingBtn.setVisible(true);
            smoothScalingBtn.setVisible(true);

          } else {
            musicBtn.setVisible(false);
            soundFxBtn.setVisible(false);
            viewSettings = false;
            creditsBtn.setVisible(false);
            resolutionOptions.setVisible(false);
            volumeImg.setVisible(false);
            incrVolumeBtn.setVisible(false);
            decrVolumeBtn.setVisible(false);
            standardScalingBtn.setVisible(false);
            noScalingBtn.setVisible(false);
            integerScalingBtn.setVisible(false);
            smoothScalingBtn.setVisible(false);
            bg.setEffect(null);
            showItemsOnScreen();
            if (!isHome) {
              backBtn.setVisible(true);
            }
          }
        });

    root.getChildren().add(settingsBtn);

    startMGameBtn = new Button();
    StackPane.setAlignment(startMGameBtn, Pos.BOTTOM_CENTER);
    StackPane.setMargin(startMGameBtn, new Insets(0, 0, 200, 0));

    startingM = new ImageView(startImg);
    startMGameBtn.setGraphic(startingM);
    startMGameBtn.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(startMGameBtn);
    startMGameBtn.setVisible(false);
    startMGameBtn.setOnAction(
        e -> {
          audioController.playSound(Sounds.click);
          client.startMultiplayerGame();
        });

    backBtn = new Button();
    StackPane.setAlignment(backBtn, Pos.BOTTOM_CENTER);
    StackPane.setMargin(backBtn, new Insets(0, 0, 100, 0));
    Image backImg = new Image("ui/back.png");
    backImageView = new ImageView(backImg);
    backBtn.setGraphic(backImageView);
    backBtn.setStyle("-fx-background-color: transparent;");
    backBtn.setOnAction(
        event -> {
          audioController.playSound(Sounds.click);

          if (!backTree.isEmpty()) {
            for (Node item : itemsOnScreen) {
              item.setVisible(false);
            }
            itemsOnScreen.clear();
            ArrayList<Node> toShow = backTree.pop();
            for (Node item : toShow) {
              item.setVisible(true);
              itemsOnScreen.add(item);
            }
            if (backTree.isEmpty()) {
              backBtn.setVisible(false);
              isHome = true;
            }
          }
        });

    backBtn.setVisible(false);
    root.getChildren().add(backBtn);

    backTree.empty();
    itemsOnScreen.add(playBtn);

    imageViews =
        Arrays.asList(
            volumeImg,
            lowResImageView,
            medResImageView,
            highResImageView,
            playView,
            logo,
            starting,
            singlePlayerImageView,
            multiplayerImageView,
            musicOnView,
            fxView,
            incrView,
            decrView,
            creditsView,
            quitView,
            joinGameView,
            createGameView,
            createLobbyView,
            continueView,
            settingsView,
            backImageView,
            startingM);

    for (int i = 0; i < imageViews.size(); i++) {
      originalViewWidths.add(imageViews.get(i).getBoundsInLocal().getWidth());
      minimumViewWidths.add(originalViewWidths.get(i) * 0.4);
    }

    return root;
  }

  /**
   * @param newVal the new screen width.
   * @param oldVal the old screen width.
   * @author Adam Kona Updates the current size of all the images in the menu whilst preserving
   *     their aspect ratio. The percentage change in the screen width is calculated and the size of
   *     the images is changed along with it as long as this does not fall below 40% the original
   *     image size and does not rise above the original image size.
   */
  public void scaleImages(double newVal, double oldVal) {

    for (int i = 0; i < imageViews.size(); i++) {
      ImageView currentView = imageViews.get(i);
      double currentWidth = currentView.getBoundsInLocal().getWidth();
      currentView.setPreserveRatio(true);
      currentView.setSmooth(true);

      double proposedWidth = Math.floor(currentWidth * (newVal / oldVal));
      if (proposedWidth > originalViewWidths.get(i)) {
        currentView.setFitWidth(originalViewWidths.get(i));
      } else if (proposedWidth < minimumViewWidths.get(i)) {
        currentView.setFitWidth(minimumViewWidths.get(i));
      } else {
        currentView.setFitWidth(Math.floor(currentWidth * (newVal / oldVal)));
      }
    }
  }

  public void initialiseRenderingButtons(
      RadioButton rBtn, int xMargin, int yMargin, StackPane root, ToggleGroup g, RenderingMode rm) {
    rBtn.setVisible(false);
    StackPane.setAlignment(rBtn, Pos.CENTER);
    StackPane.setMargin(rBtn, new Insets(yMargin, xMargin, 0, xMargin));
    root.getChildren().add(rBtn);
    rBtn.setOnAction(event -> client.setRenderingMode(rm));
    rBtn.setToggleGroup(g);
  }
}
