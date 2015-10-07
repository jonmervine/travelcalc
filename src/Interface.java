
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileReader;

/**
 *
 * @author  ORi
 */
public class Interface extends javax.swing.JFrame {

    String AppVersion = "3.10";
    Crit[] owncreatures = new Crit[55];
    Crit[] creatures = new Crit[55];
    int CritAantal = 0;
    int OwnCritAantal = 0;
    /* debug features NORMAL: true DEBUGMODE: false */
    boolean TravStatChoice = true;
    boolean DecodePass = true;
    int DeadCrits = 0;
    int DIPCrits = 0;
    StringBuilder gevecht = new StringBuilder("");
    int ownExtraIthHealth = 0;
    int ownExtraIthDamage = 0;
    int ownExtraIthDefences = 0;
    int StatId = 0;
    int ExtraIthHealth = 0;
    int ExtraIthDamage = 0;
    int ExtraIthDefences = 0;
    String AttackingString = "";
    String username = "";
    String userpass = "";
    String NoAcces = "NO ACCESS: Seriously, login damnit!";
    int AantalBadCalcs = 0;
    int AantalGoodCalcs = 0;
    boolean loggedIn = false;
    int[][] nummers = new int[55][2];
    boolean ToggleOnTop = false;
    boolean ToggleSmallWindow = false;
    boolean ShowFullBattles = false;
    boolean ToggleClipListener = true;
    boolean ToggleResCalc = true;
    String okSound = "ok.wav";
    String warningSound = "warning.wav";
    String failureSound = "failure.wav";
    private static Logger theLogger = Logger.getLogger(Interface.class.getName());
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    Field field;
    Integer Brim = 0;
    Integer Crys = 0;
    Integer Ess = 0;
    Integer Gran = 0;
    Integer Total = 0;
    Integer Brim2 = 0;
    Integer Crys2 = 0;
    Integer Ess2 = 0;
    Integer Gran2 = 0;
    Integer Total2 = 0;
    int BrimPrev = 0;
    int CrysPrev = 0;
    int EssPrev = 0;
    int GranPrev = 0;
    int BattleNum = 1;
    boolean AutoCalcFromClipboard = true;
    /* properties */
    Preferences prefs = Preferences.userNodeForPackage(this.getClass());

    /** Creates new form Interface */
    public Interface() {
        try {
            FileHandler fh = new FileHandler("TravCalcByORi.log", true); /* true means append */
            fh.setFormatter(new SimpleFormatter());
            theLogger.addHandler(fh);

        } catch (SecurityException ex) {
            theLogger.severe("ERROR: " + ex.toString());
        } catch (IOException ex) {
            theLogger.severe("ERROR: " + ex.toString());
        } /* true means append */

        /* start - admin version only */
        // System.setProperty("socksProxyHost", "127.0.0.1");
        // System.setProperty("socksProxyPort", "3080");
        /* end - admin version only */

        initComponents();
        pack();
        setVisible(true);
        setTitle("TravelCalc by ORi (v" + AppVersion + ")");
        setLocationRelativeTo(null);

        Total = 0;

        frmLogin.setSize(300, 300);
        frmLogin.setFocusable(true);
        frmLogin.requestFocus();
        frmLogin.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frmLogin.setTitle("TravelCalc login");

        frmLogin.getRootPane().setDefaultButton(btnLogin);

        frmLogin.pack();
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(true);

        frmCreatureListUpdate.setTitle("Your creature list as parsed from database.");
        critlistField.setText("");
        frmCreatureListUpdate.setSize(600, 600);
        frmCreatureListUpdate.setLocationRelativeTo(null);
        frmCreatureListUpdate.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        frmCreatureListUpdate.setSize(700, 400);
        frmResCalc.pack();
        frmResCalc.setLocationRelativeTo(null);

        txtUserName.requestFocus();





        ClipboardMonitor monitor = ClipboardMonitor.getMonitor();
        monitor.addObserver(new Observer() {

            public void update(Observable o, Object arg) {
                System.out.println("Clipboard has been regained!");

                ClipboardDataChanged();
                DoCountRes();
            }
        });


        if (!checkLatestVersion()) {
            txtLoginStatus.setText("You are not using the latest version!");
            txtUserName.setEditable(false);
            txtUserPassword.setEditable(false);
            btnLogin.setEnabled(false);
        }


        /* rescalc verbergen */
        //ResPanel.setVisible(false);

        /* properties ophalen */

        //prefs.put("_TRAVCALC_USER_NAME_", "test");
        String naampje = prefs.get("_TRAVCALC_USER_NAME_", "");
        username = naampje;

        if (!naampje.equals("")) {
            txtUserName.setText(naampje);
            txtUserPassword.requestFocus();
        }
        pack();


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    private void initComponents() {

        frmLogin = new javax.swing.JDialog();
        lblWelcome = new javax.swing.JLabel();
        lblWelcome2 = new javax.swing.JLabel();
        lblWelcome3 = new javax.swing.JLabel();
        lblWelcome4 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtUserPassword = new javax.swing.JPasswordField();
        txtLoginStatus = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        frmCreatureListUpdate = new javax.swing.JDialog();
        pnlCreatureList = new javax.swing.JScrollPane();
        critlistField = new javax.swing.JEditorPane();
        btnResyncList = new javax.swing.JButton();
        frmMoreInfo = new javax.swing.JDialog();
        lblInfo = new javax.swing.JLabel();
        frmResCalc = new javax.swing.JDialog();
        pnlResCalc = new javax.swing.JPanel();
        lblHeaderBefore = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtResInputAfter = new javax.swing.JTextArea();
        btnAfterResCalc = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        GranPanel5 = new javax.swing.JPanel();
        lblGranRes2 = new javax.swing.JLabel();
        lblLabelGran5 = new javax.swing.JLabel();
        EssPanel5 = new javax.swing.JPanel();
        lblEssRes2 = new javax.swing.JLabel();
        lblLabelEss5 = new javax.swing.JLabel();
        CrysPanel5 = new javax.swing.JPanel();
        lblLabelCrys5 = new javax.swing.JLabel();
        lblCrysRes2 = new javax.swing.JLabel();
        BrimPanel5 = new javax.swing.JPanel();
        lblLabelBrim5 = new javax.swing.JLabel();
        lblBrimRes2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnBeforeResCalc = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResInputBefore = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        GranPanel3 = new javax.swing.JPanel();
        lblGranRes1 = new javax.swing.JLabel();
        lblLabelGran3 = new javax.swing.JLabel();
        EssPanel3 = new javax.swing.JPanel();
        lblEssRes1 = new javax.swing.JLabel();
        lblLabelEss3 = new javax.swing.JLabel();
        CrysPanel3 = new javax.swing.JPanel();
        lblLabelCrys3 = new javax.swing.JLabel();
        lblCrysRes1 = new javax.swing.JLabel();
        BrimPanel3 = new javax.swing.JPanel();
        lblLabelBrim3 = new javax.swing.JLabel();
        lblBrimRes1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        GranPanel2 = new javax.swing.JPanel();
        GranPanel4 = new javax.swing.JPanel();
        lblGranRes3 = new javax.swing.JLabel();
        lblLabelGran4 = new javax.swing.JLabel();
        EssPanel2 = new javax.swing.JPanel();
        lblEssRes3 = new javax.swing.JLabel();
        lblLabelEss2 = new javax.swing.JLabel();
        CrysPanel2 = new javax.swing.JPanel();
        lblLabelCrys2 = new javax.swing.JLabel();
        lblCrysRes3 = new javax.swing.JLabel();
        BrimPanel2 = new javax.swing.JPanel();
        lblLabelBrim2 = new javax.swing.JLabel();
        lblBrimRes3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTotalRes3 = new javax.swing.JLabel();
        lblLabelGran2 = new javax.swing.JLabel();
        frmNews = new javax.swing.JDialog();
        frmNewsPanel = new javax.swing.JScrollPane();
        txtHTMLField = new javax.swing.JEditorPane();
        pnlBoven = new javax.swing.JPanel();
        pnlInput = new javax.swing.JPanel();
        sbInput = new javax.swing.JScrollPane();
        txtInput = new javax.swing.JTextArea();
        pnlOutput = new javax.swing.JPanel();
        sbOutput = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JEditorPane();
        pnlOnder = new javax.swing.JPanel();
        pnlMidden = new javax.swing.JPanel();
        txtStatus = new javax.swing.JTextField();
        pnlButtons = new javax.swing.JPanel();
        btnCountResources = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        PasteCalcButton = new javax.swing.JButton();
        CalcButton = new javax.swing.JButton();
        pnlResources = new javax.swing.JPanel();
        ResBrimField = new javax.swing.JTextField();
        ResCrysField = new javax.swing.JTextField();
        ResEssField = new javax.swing.JTextField();
        ResGranField = new javax.swing.JTextField();
        ResTotalField = new javax.swing.JTextField();
        BrimLabel = new javax.swing.JLabel();
        CrysLabel = new javax.swing.JLabel();
        EssLabel = new javax.swing.JLabel();
        GranLabel = new javax.swing.JLabel();
        TotalLabel = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        MainMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        ItemSeeCritList = new javax.swing.JMenuItem();
        ItemShowRes = new javax.swing.JMenuItem();
        ItemShowBug = new javax.swing.JMenuItem();
        ItemMoreInfo = new javax.swing.JMenuItem();
        OptionsMenu = new javax.swing.JMenu();
        ItemShowFullBattles = new javax.swing.JCheckBoxMenuItem();
        ItemSetAlwaysOnTop = new javax.swing.JCheckBoxMenuItem();
        ItemToggleSmallWindow = new javax.swing.JCheckBoxMenuItem();
        ItemShowResCalc = new javax.swing.JCheckBoxMenuItem();
        ItemAutoCalcFromClipboard = new javax.swing.JCheckBoxMenuItem();
        ItemSoundToggle = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        ItemClearResources = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        ItemSaveSettings = new javax.swing.JMenuItem();

        frmLogin.setAlwaysOnTop(true);
        frmLogin.addWindowFocusListener(new java.awt.event.WindowFocusListener() {

            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }

            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                frmLoginWindowLostFocus(evt);
            }
        });
        frmLogin.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosed(java.awt.event.WindowEvent evt) {
                frmLoginWindowClosed(evt);
            }

            public void windowOpened(java.awt.event.WindowEvent evt) {
                frmLoginWindowOpened(evt);
            }
        });

        lblWelcome.setText("Have your list in c:\\list.txt and press login");
        lblUsername.setFont(new java.awt.Font("Tahoma", 0, 12));
        lblUsername.setText("Username :");
        lblUsername.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsernameMouseClicked(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Tahoma", 0, 12));
        lblPassword.setText("Password :");

        txtUserPassword.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserPasswordActionPerformed(evt);
            }
        });

        txtLoginStatus.setEditable(false);
        txtLoginStatus.setText("  Enter your username & password and press login!");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout frmLoginLayout = new org.jdesktop.layout.GroupLayout(frmLogin.getContentPane());
        frmLogin.getContentPane().setLayout(frmLoginLayout);
        frmLoginLayout.setHorizontalGroup(
                frmLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(frmLoginLayout.createSequentialGroup().add(frmLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(frmLoginLayout.createSequentialGroup().addContainerGap().add(frmLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(lblUsername).add(lblPassword)).add(13, 13, 13).add(frmLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false).add(txtUserName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE).add(txtUserPassword, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE).add(btnLogin, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).add(42, 42, 42)).add(frmLoginLayout.createSequentialGroup().addContainerGap().add(lblWelcome)).add(frmLoginLayout.createSequentialGroup().add(53, 53, 53).add(frmLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(lblWelcome3).add(lblWelcome2).add(lblWelcome4))).add(frmLoginLayout.createSequentialGroup().addContainerGap().add(txtLoginStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))).addContainerGap()));
        frmLoginLayout.setVerticalGroup(
                frmLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(frmLoginLayout.createSequentialGroup().addContainerGap().add(lblWelcome).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lblWelcome2).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lblWelcome3).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lblWelcome4).add(25, 25, 25).add(frmLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(lblUsername).add(txtUserName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(frmLoginLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(lblPassword).add(txtUserPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnLogin).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(txtLoginStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        frmLogin.getAccessibleContext().setAccessibleParent(this);

        frmCreatureListUpdate.setAlwaysOnTop(true);

        critlistField.setContentType("text/html");
        critlistField.setEditable(false);
        pnlCreatureList.setViewportView(critlistField);

        btnResyncList.setText("Resynchronise list (press this button after you've updated your list on cq2.stormy.be)");
        btnResyncList.setActionCommand("Resynchronise List (Press this button after you've updated your list on cq2.stormy.be)");
        btnResyncList.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResyncListActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout frmCreatureListUpdateLayout = new org.jdesktop.layout.GroupLayout(frmCreatureListUpdate.getContentPane());
        frmCreatureListUpdate.getContentPane().setLayout(frmCreatureListUpdateLayout);
        frmCreatureListUpdateLayout.setHorizontalGroup(
                frmCreatureListUpdateLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlCreatureList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE).add(btnResyncList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE));
        frmCreatureListUpdateLayout.setVerticalGroup(
                frmCreatureListUpdateLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(org.jdesktop.layout.GroupLayout.TRAILING, frmCreatureListUpdateLayout.createSequentialGroup().add(btnResyncList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(pnlCreatureList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)));

        frmCreatureListUpdate.getAccessibleContext().setAccessibleName("Creature Listing");
        frmCreatureListUpdate.getAccessibleContext().setAccessibleParent(this);

        frmMoreInfo.setTitle("Credits & Information");
        frmMoreInfo.setAlwaysOnTop(true);
        frmMoreInfo.setFocusTraversalPolicyProvider(true);

        lblInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/travelcalc/splash.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout frmMoreInfoLayout = new org.jdesktop.layout.GroupLayout(frmMoreInfo.getContentPane());
        frmMoreInfo.getContentPane().setLayout(frmMoreInfoLayout);
        frmMoreInfoLayout.setHorizontalGroup(
                frmMoreInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(lblInfo));
        frmMoreInfoLayout.setVerticalGroup(
                frmMoreInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(lblInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 294, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));

        frmMoreInfo.getAccessibleContext().setAccessibleParent(this);

        frmResCalc.setTitle("Resource Calculator");
        frmResCalc.setAlwaysOnTop(true);
        frmResCalc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        frmResCalc.setFocusTraversalPolicyProvider(true);
        frmResCalc.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeaderBefore.setText("Copy your resources BEFORE travelling to this form:");

        jLabel9.setText("Copy your resources AFTER travelling to this form:");

        txtResInputAfter.setColumns(20);
        txtResInputAfter.setRows(5);
        jScrollPane3.setViewportView(txtResInputAfter);

        btnAfterResCalc.setText("SET ->");
        btnAfterResCalc.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAfterResCalcActionPerformed(evt);
            }
        });

        GranPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGranRes2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGranRes2.setText("0");
        GranPanel5.add(lblGranRes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 50, -1));

        lblLabelGran5.setText("Granite:");
        GranPanel5.add(lblLabelGran5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        EssPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEssRes2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEssRes2.setText("0");
        EssPanel5.add(lblEssRes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 50, -1));

        lblLabelEss5.setText("Essence:");
        EssPanel5.add(lblLabelEss5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        CrysPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLabelCrys5.setText("Crystal:");
        CrysPanel5.add(lblLabelCrys5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblCrysRes2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCrysRes2.setText("0");
        CrysPanel5.add(lblCrysRes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 50, -1));

        BrimPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLabelBrim5.setText("Brimstone:");
        BrimPanel5.add(lblLabelBrim5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblBrimRes2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBrimRes2.setText("0");
        BrimPanel5.add(lblBrimRes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 50, -1));

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel9Layout.createSequentialGroup().addContainerGap().add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING).add(org.jdesktop.layout.GroupLayout.LEADING, CrysPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, BrimPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, EssPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, GranPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel9Layout.createSequentialGroup().addContainerGap().add(BrimPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(CrysPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(EssPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(GranPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel4Layout.createSequentialGroup().addContainerGap().add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnAfterResCalc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel4Layout.createSequentialGroup().add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false).add(btnAfterResCalc, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE).add(jScrollPane3)).add(jPanel4Layout.createSequentialGroup().addContainerGap().add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        btnBeforeResCalc.setText("SET ->");
        btnBeforeResCalc.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeforeResCalcActionPerformed(evt);
            }
        });

        txtResInputBefore.setColumns(20);
        txtResInputBefore.setRows(5);
        jScrollPane2.setViewportView(txtResInputBefore);

        GranPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGranRes1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGranRes1.setText("0");
        GranPanel3.add(lblGranRes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 50, -1));

        lblLabelGran3.setText("Granite:");
        GranPanel3.add(lblLabelGran3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        EssPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEssRes1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEssRes1.setText("0");
        EssPanel3.add(lblEssRes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 50, -1));

        lblLabelEss3.setText("Essence:");
        EssPanel3.add(lblLabelEss3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        CrysPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLabelCrys3.setText("Crystal:");
        CrysPanel3.add(lblLabelCrys3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblCrysRes1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCrysRes1.setText("0");
        CrysPanel3.add(lblCrysRes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 50, -1));

        BrimPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLabelBrim3.setText("Brimstone:");
        BrimPanel3.add(lblLabelBrim3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblBrimRes1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBrimRes1.setText("0");
        BrimPanel3.add(lblBrimRes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 50, -1));

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup().addContainerGap().add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING).add(org.jdesktop.layout.GroupLayout.LEADING, CrysPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, BrimPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, EssPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, GranPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel7Layout.createSequentialGroup().addContainerGap().add(BrimPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(CrysPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(EssPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(GranPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel6Layout.createSequentialGroup().addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnBeforeResCalc).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(40, 40, 40)));
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel6Layout.createSequentialGroup().add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel6Layout.createSequentialGroup().addContainerGap().add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false).add(btnBeforeResCalc, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))).add(jPanel6Layout.createSequentialGroup().add(24, 24, 24).add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 100));
        jLabel10.setText("}");

        GranPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GranPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGranRes3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGranRes3.setText("0");
        GranPanel4.add(lblGranRes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 70, -1));

        lblLabelGran4.setText("Granite:");
        GranPanel4.add(lblLabelGran4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        GranPanel2.add(GranPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        EssPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEssRes3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEssRes3.setText("0");
        EssPanel2.add(lblEssRes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 70, -1));

        lblLabelEss2.setText("Essence:");
        EssPanel2.add(lblLabelEss2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        CrysPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLabelCrys2.setText("Crystal:");
        CrysPanel2.add(lblLabelCrys2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblCrysRes3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCrysRes3.setText("0");
        CrysPanel2.add(lblCrysRes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 70, -1));

        BrimPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLabelBrim2.setText("Brimstone:");
        BrimPanel2.add(lblLabelBrim2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblBrimRes3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBrimRes3.setText("0");
        BrimPanel2.add(lblBrimRes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 70, -1));

        lblTotalRes3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalRes3.setText("0");

        lblLabelGran2.setText("Total:");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel3Layout.createSequentialGroup().addContainerGap().add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup().add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING).add(org.jdesktop.layout.GroupLayout.LEADING, CrysPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, BrimPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, EssPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, GranPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()).add(jPanel3Layout.createSequentialGroup().add(lblLabelGran2).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 13, Short.MAX_VALUE).add(lblTotalRes3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addContainerGap()))));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel3Layout.createSequentialGroup().addContainerGap().add(BrimPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(CrysPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(EssPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(GranPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(9, 9, 9).add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(lblLabelGran2).add(lblTotalRes3)).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        org.jdesktop.layout.GroupLayout pnlResCalcLayout = new org.jdesktop.layout.GroupLayout(pnlResCalc);
        pnlResCalc.setLayout(pnlResCalcLayout);
        pnlResCalcLayout.setHorizontalGroup(
                pnlResCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlResCalcLayout.createSequentialGroup().add(pnlResCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlResCalcLayout.createSequentialGroup().add(pnlResCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false).add(pnlResCalcLayout.createSequentialGroup().addContainerGap().add(jLabel9)).add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jPanel6, 0, 406, Short.MAX_VALUE)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel10).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).add(pnlResCalcLayout.createSequentialGroup().addContainerGap().add(lblHeaderBefore))).addContainerGap(138, Short.MAX_VALUE)));
        pnlResCalcLayout.setVerticalGroup(
                pnlResCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlResCalcLayout.createSequentialGroup().add(pnlResCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlResCalcLayout.createSequentialGroup().add(lblHeaderBefore).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(pnlResCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 294, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(pnlResCalcLayout.createSequentialGroup().add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(jLabel9).add(14, 14, 14).add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))).add(pnlResCalcLayout.createSequentialGroup().add(123, 123, 123).add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        frmResCalc.getContentPane().add(pnlResCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        frmResCalc.getAccessibleContext().setAccessibleParent(this);

        frmNews.setAlwaysOnTop(true);

        txtHTMLField.setContentType("text/html");
        txtHTMLField.setEditable(false);
        frmNewsPanel.setViewportView(txtHTMLField);

        org.jdesktop.layout.GroupLayout frmNewsLayout = new org.jdesktop.layout.GroupLayout(frmNews.getContentPane());
        frmNews.getContentPane().setLayout(frmNewsLayout);
        frmNewsLayout.setHorizontalGroup(
                frmNewsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(frmNewsLayout.createSequentialGroup().add(frmNewsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE).addContainerGap()));
        frmNewsLayout.setVerticalGroup(
                frmNewsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(frmNewsLayout.createSequentialGroup().addContainerGap().add(frmNewsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE).addContainerGap()));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {

            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }

            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }

            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlBoven.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        sbInput.setViewportView(txtInput);

        org.jdesktop.layout.GroupLayout pnlInputLayout = new org.jdesktop.layout.GroupLayout(pnlInput);
        pnlInput.setLayout(pnlInputLayout);
        pnlInputLayout.setHorizontalGroup(
                pnlInputLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlInputLayout.createSequentialGroup().addContainerGap().add(sbInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE).addContainerGap()));
        pnlInputLayout.setVerticalGroup(
                pnlInputLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlInputLayout.createSequentialGroup().addContainerGap().add(sbInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE).addContainerGap()));

        pnlBoven.add(pnlInput);

        txtOutput.setContentType("text/html");
        txtOutput.setEditable(false);
        sbOutput.setViewportView(txtOutput);

        org.jdesktop.layout.GroupLayout pnlOutputLayout = new org.jdesktop.layout.GroupLayout(pnlOutput);
        pnlOutput.setLayout(pnlOutputLayout);
        pnlOutputLayout.setHorizontalGroup(
                pnlOutputLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlOutputLayout.createSequentialGroup().addContainerGap().add(sbOutput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE).addContainerGap()));
        pnlOutputLayout.setVerticalGroup(
                pnlOutputLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(sbOutput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE));

        pnlBoven.add(pnlOutput);

        getContentPane().add(pnlBoven, java.awt.BorderLayout.CENTER);

        pnlOnder.setLayout(new java.awt.BorderLayout());

        txtStatus.setEditable(false);
        txtStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtStatus.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusActionPerformed(evt);
            }
        });

        btnCountResources.setText("Count Resources");
        btnCountResources.setPreferredSize(new java.awt.Dimension(150, 25));
        btnCountResources.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountResourcesActionPerformed(evt);
            }
        });

        ClearButton.setText("Clear Battle");
        ClearButton.setPreferredSize(new java.awt.Dimension(150, 25));
        ClearButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        PasteCalcButton.setText("Calc from Clipboard");
        PasteCalcButton.setPreferredSize(new java.awt.Dimension(150, 25));
        PasteCalcButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasteCalcButtonActionPerformed(evt);
            }
        });

        CalcButton.setText("Calc from Input");
        CalcButton.setPreferredSize(new java.awt.Dimension(150, 25));
        CalcButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlButtonsLayout = new org.jdesktop.layout.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
                pnlButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlButtonsLayout.createSequentialGroup().addContainerGap().add(CalcButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(PasteCalcButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(ClearButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(btnCountResources, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)));
        pnlButtonsLayout.setVerticalGroup(
                pnlButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(CalcButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(PasteCalcButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(ClearButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(btnCountResources, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));

        org.jdesktop.layout.GroupLayout pnlMiddenLayout = new org.jdesktop.layout.GroupLayout(pnlMidden);
        pnlMidden.setLayout(pnlMiddenLayout);
        pnlMiddenLayout.setHorizontalGroup(
                pnlMiddenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlMiddenLayout.createSequentialGroup().add(pnlMiddenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlMiddenLayout.createSequentialGroup().addContainerGap().add(txtStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)).add(pnlButtons, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        pnlMiddenLayout.setVerticalGroup(
                pnlMiddenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlMiddenLayout.createSequentialGroup().addContainerGap().add(txtStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(pnlButtons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pnlOnder.add(pnlMidden, java.awt.BorderLayout.NORTH);

        ResBrimField.setEditable(false);
        ResBrimField.setText("0");

        ResCrysField.setEditable(false);
        ResCrysField.setText("0");
        ResCrysField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResCrysFieldActionPerformed(evt);
            }
        });

        ResEssField.setEditable(false);
        ResEssField.setText("0");
        ResEssField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResEssFieldActionPerformed(evt);
            }
        });

        ResGranField.setEditable(false);
        ResGranField.setText("0");

        ResTotalField.setEditable(false);
        ResTotalField.setText("0");
        ResTotalField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResTotalFieldActionPerformed(evt);
            }
        });

        BrimLabel.setText("Brim:");

        CrysLabel.setText("Crys:");

        EssLabel.setText("Ess: ");

        GranLabel.setText("Gran:");

        TotalLabel.setText("Total:");

        org.jdesktop.layout.GroupLayout pnlResourcesLayout = new org.jdesktop.layout.GroupLayout(pnlResources);
        pnlResources.setLayout(pnlResourcesLayout);
        pnlResourcesLayout.setHorizontalGroup(
                pnlResourcesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlResourcesLayout.createSequentialGroup().addContainerGap().add(BrimLabel).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(ResBrimField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(CrysLabel).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(ResCrysField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(EssLabel).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(ResEssField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(GranLabel).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(ResGranField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(TotalLabel).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(ResTotalField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE).addContainerGap()));
        pnlResourcesLayout.setVerticalGroup(
                pnlResourcesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(pnlResourcesLayout.createSequentialGroup().add(pnlResourcesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(ResCrysField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(ResEssField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(ResGranField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(ResTotalField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(CrysLabel).add(EssLabel).add(GranLabel).add(TotalLabel).add(ResBrimField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(BrimLabel)).addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pnlOnder.add(pnlResources, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlOnder, java.awt.BorderLayout.SOUTH);

        MenuBar.setAutoscrolls(true);

        MainMenu.setText("Menu");
        MainMenu.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuActionPerformed(evt);
            }
        });

        jMenuItem1.setText("News");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MainMenu.add(jMenuItem1);

        ItemSeeCritList.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        ItemSeeCritList.setText("Show Creature List");
        ItemSeeCritList.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeeCritListActionPerformed(evt);
            }
        });
        MainMenu.add(ItemSeeCritList);

        ItemShowRes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        ItemShowRes.setText("Show Resource Calc");
        ItemShowRes.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemShowResActionPerformed(evt);
            }
        });
        MainMenu.add(ItemShowRes);

        ItemShowBug.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        ItemShowBug.setText("Report Bug");
        ItemShowBug.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBugButtonActionPerformed(evt);
            }
        });
        MainMenu.add(ItemShowBug);

        ItemMoreInfo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        ItemMoreInfo.setText("More Info");
        ItemMoreInfo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemMoreInfoActionPerformed(evt);
            }
        });
        MainMenu.add(ItemMoreInfo);

        MenuBar.add(MainMenu);

        OptionsMenu.setText("Options");
        OptionsMenu.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptionsMenuActionPerformed(evt);
            }
        });

        ItemShowFullBattles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        ItemShowFullBattles.setText("Show Full Battles");
        ItemShowFullBattles.setActionCommand("Full Battles");
        ItemShowFullBattles.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowFullBattlesActionPerformed(evt);
            }
        });
        ItemShowFullBattles.addAncestorListener(new javax.swing.event.AncestorListener() {

            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }

            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ItemShowFullBattlesAncestorAdded(evt);
            }

            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        OptionsMenu.add(ItemShowFullBattles);

        ItemSetAlwaysOnTop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        ItemSetAlwaysOnTop.setText("Always On Top");
        ItemSetAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemSetAlwaysOnTopActionPerformed(evt);
            }
        });
        OptionsMenu.add(ItemSetAlwaysOnTop);

        ItemToggleSmallWindow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        ItemToggleSmallWindow.setText("Basic Window Version");
        ItemToggleSmallWindow.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemToggleSmallWindowActionPerformed(evt);
            }
        });
        OptionsMenu.add(ItemToggleSmallWindow);

        ItemShowResCalc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        ItemShowResCalc.setSelected(true);
        ItemShowResCalc.setActionCommand("Encounter Rescalc");
        ItemShowResCalc.setLabel("Show Encounter Rescalc");
        ItemShowResCalc.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemShowResCalcActionPerformed(evt);
            }
        });
        OptionsMenu.add(ItemShowResCalc);

        ItemAutoCalcFromClipboard.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        ItemAutoCalcFromClipboard.setSelected(true);
        ItemAutoCalcFromClipboard.setText("Auto Calc From Clipboard");
        ItemAutoCalcFromClipboard.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemToggleAutoClipboardActionPerformed(evt);
            }
        });
        OptionsMenu.add(ItemAutoCalcFromClipboard);

        ItemSoundToggle.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        ItemSoundToggle.setSelected(true);
        ItemSoundToggle.setText("Sounds");
        ItemSoundToggle.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemSoundToggleActionPerformed(evt);
            }
        });
        OptionsMenu.add(ItemSoundToggle);
        OptionsMenu.add(jSeparator2);

        ItemClearResources.setText("Clear Resources");
        ItemClearResources.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemClearResourcesActionPerformed(evt);
            }
        });
        OptionsMenu.add(ItemClearResources);
        OptionsMenu.add(jSeparator3);

        ItemSaveSettings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        ItemSaveSettings.setText("Save Current Settings");
        ItemSaveSettings.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemSaveSettingsActionPerformed(evt);
            }
        });
        OptionsMenu.add(ItemSaveSettings);

        MenuBar.add(OptionsMenu);

        setJMenuBar(MenuBar);

        getAccessibleContext().setAccessibleName("MainTravCalcFrame");

        pack();
    }// </editor-fold>                        

    private void ItemSoundToggleActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        if (ItemSoundToggle.isSelected()) {
            playSound(0);
        }
    }

    private void MainMenuActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
    }

    private void ItemShowResActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        if (loggedIn) {
            frmResCalc.setVisible(true);
        }
    }

    private void frmLoginWindowOpened(java.awt.event.WindowEvent evt) {
    }

    private void lblUsernameMouseClicked(java.awt.event.MouseEvent evt) {
// TODO add your handling code here:
    }

    private void btnResyncListActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        OwnCritAantal = 0;
        DoLogin();
        GenerateListView();

    }

    private void frmLoginWindowClosed(java.awt.event.WindowEvent evt) {
// TODO add your handling code here:
        // this.dispose();
    }

    public String getMD5Hash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(str.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
            theLogger.severe("No MD5 algorythm found: " + e.toString());
        } catch (UnsupportedEncodingException e) {
            theLogger.severe("No MD5 encoding supported: " + e.toString());
        }
        return null;

    }

    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
        }
        return sb.toString();
    }

    private void ItemShowResCalcActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            if (ToggleResCalc) {
                HideResCalc();
            } else {
                ShowResCalc();
            }
        } else {
            SetStatus(NoAcces);
        }
    }

    private void ShowResCalc() {
        ToggleResCalc = true;
        pnlResources.setVisible(true);
        ItemShowResCalc.setState(true);
        btnCountResources.setVisible(true);
        if (ToggleSmallWindow) {
            this.setSize(400, 160);
            ClearButton.setVisible(false);
            getLayout().layoutContainer(this);
        }

    }

    private void HideResCalc() {
        ToggleResCalc = false;
        pnlResources.setVisible(false);
        ItemShowResCalc.setState(false);
        btnCountResources.setVisible(false);
        if (ToggleSmallWindow) {
            this.setSize(400, 130);
            ClearButton.setVisible(true);
            getLayout().layoutContainer(this);
        }

    }

    private void OptionsMenuActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
    }

    private void txtStatusActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
    }

    private void GenerateListView() {
        frmCreatureListUpdate.setTitle("Your creature list as parsed from database.");
        String totalStr = "<html><body><table>";
        for (int i = 0; i < OwnCritAantal; i++) {

            String enchant = "";
            if (owncreatures[i].getMaIm().length() > 0) {
                enchant = "* (<font color='#FF8000'>MaIm</font> " + owncreatures[i].getMaIm() + ")";
            } else if (owncreatures[i].getMaBe().length() > 0) {
                enchant = "* (<font color='#800000'>MaBe</font> " + owncreatures[i].getMaBe() + ")";
            }

            String klas = "";
            if (owncreatures[i].getType().equals("forest")) {
                klas = "<font color='#004000'>forest</font>";
            } else if (owncreatures[i].getType().equals("air")) {
                klas = "<font color='#004080'>air</font>";
            } else if (owncreatures[i].getType().equals("earth")) {
                klas = "<font color='#FF8040'>earth</font>";
            } else if (owncreatures[i].getType().equals("death")) {
                klas = "<font color='#800000'>death</font>";
            }

            totalStr += "<tr><td>" + (i + 1) + ".</td><td><b>" + owncreatures[i].getName() + "</b></td><td>" + owncreatures[i].getFamily() + "/" + klas + "</td><td>" + owncreatures[i].getLevel() + "</td><td><font color='#800000'>" + owncreatures[i].getDamage() + "</font>/<font color='#800000'>" + owncreatures[i].getHealth() + "</font></td><td>F" + owncreatures[i].getForestDefence() + "/D" + owncreatures[i].getDeathDefence() + "/A" + owncreatures[i].getAirDefence() + "/E" + owncreatures[i].getEarthDefence() + "</td><td><font color='#000080'>" + owncreatures[i].getItem() + "</font>" + enchant + "</td></tr>";
        }
        totalStr += "</table></body></html>";
        critlistField.setText(totalStr);
        critlistField.setCaretPosition(0);
        critlistField.setEditable(false);
        frmCreatureListUpdate.pack();
        frmCreatureListUpdate.setVisible(true);
    }

    private void SeeCritListActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            btnResyncList.setEnabled(true);
            btnResyncList.setVisible(true);

            GenerateListView();
        } else {
            SetStatus(NoAcces);
        }
    }

    private void reportBugButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            btnResyncList.setEnabled(false);
            btnResyncList.setVisible(false);

            frmCreatureListUpdate.setTitle("Copy/Paste this bugreport to Database");

            String totalStr = "";
            for (int i = 0; i < OwnCritAantal; i++) {

                String enchant = "";
                if (owncreatures[i].getMaIm().length() > 0) {
                    enchant = "*(MaIm " + owncreatures[i].getMaIm() + ")";
                } else if (owncreatures[i].getMaBe().length() > 0) {
                    enchant = "*(MaBe " + owncreatures[i].getMaBe() + ")";
                }

                totalStr += owncreatures[i].getName() + "  " + owncreatures[i].getFamily() + "/" + owncreatures[i].getType() + ", passive  " + owncreatures[i].getLevel() + "  " + owncreatures[i].getDamage() + "/" + owncreatures[i].getHealth() + "  F" + owncreatures[i].getForestDefence() + "/D" + owncreatures[i].getDeathDefence() + "/A" + owncreatures[i].getAirDefence() + "/E" + owncreatures[i].getEarthDefence() + "  " + owncreatures[i].getItem() + "" + enchant + "<br>";
            }

            String totaleStr = "";
            for (int i = 0; i < CritAantal; i++) {

                totaleStr += creatures[i].getName() + "  " + creatures[i].getFamily() + "/" + creatures[i].getType() + ", passive  " + creatures[i].getLevel() + "  " + creatures[i].getDamage() + "/" + creatures[i].getHealth() + "  F" + creatures[i].getForestDefence() + "/D" + creatures[i].getDeathDefence() + "/A" + creatures[i].getAirDefence() + "/E" + creatures[i].getEarthDefence() + "  " + "<br>";
            }
            critlistField.setText("<font color=\"#CC6600\">[yellow]Description of the bug:[/yellow]</font><br><br>" + "<font color=\"#CC6600\">[yellow]Encounter:[/yellow]</font><br><br>" + totaleStr + "<br><br><font color=\"#CC6600\">[yellow]Your List:[/yellow]</font><br><br>" + totalStr + "<br><br>");
            critlistField.setCaretPosition(0);
            critlistField.setEditable(true);
            frmCreatureListUpdate.pack();
            frmCreatureListUpdate.setVisible(true);
        } else {
            SetStatus(NoAcces);
        }
    }

    private void ItemMoreInfoActionPerformed(java.awt.event.ActionEvent evt) {
        frmMoreInfo.setSize(700, 323);
        frmMoreInfo.setLocationRelativeTo(null);
        frmMoreInfo.setVisible(true);
    }

    private void ShowFullBattlesActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            FlipFullBattles();
        } else {
            SetStatus(NoAcces);
        }
    }

    private void ItemShowFullBattlesAncestorAdded(javax.swing.event.AncestorEvent evt) {
    }

    private void ItemSetAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            if (ToggleOnTop) {
                ToggleOnTop = false;
                this.setAlwaysOnTop(false);
            } else {
                ToggleOnTop = true;
                this.setAlwaysOnTop(true);
            }
        } else {
            SetStatus(NoAcces);
        }
    }

    private void ItemToggleSmallWindowActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            if (ToggleSmallWindow) {
                makeWindowLarge();
            } else {
                makeWindowSmall();
            }
        } else {
            SetStatus(NoAcces);
        }
    }

    private void makeWindowLarge() {
        ToggleSmallWindow = false;
        pnlBoven.setVisible(true);
        CalcButton.setVisible(true);
        ClearButton.setVisible(true);

        BrimLabel.setText("Brim:");
        CrysLabel.setText("Crys:");
        EssLabel.setText("Ess:");
        GranLabel.setText("Gran:");
        TotalLabel.setText("Total:");

        ItemToggleSmallWindow.setState(false);
        pack();
    }

    private void makeWindowSmall() {
        ToggleSmallWindow = true;
        ItemToggleSmallWindow.setState(true);
        pnlBoven.setVisible(false);
        CalcButton.setVisible(false);
        ClearButton.setVisible(false);

        BrimLabel.setText("B:");
        CrysLabel.setText("C:");
        EssLabel.setText("E:");
        GranLabel.setText("G:");
        TotalLabel.setText("T:");

        if (ToggleResCalc) {
            /* als de rescalc bar erbij staat */
            this.setSize(400, 160);
        } else {
            /* indien niet */
            this.setSize(400, 130);
        }
        getLayout().layoutContainer(this);
    }

    private void ItemToggleAutoClipboardActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {

            if (ToggleClipListener) {
                ToggleClipListener = false;
                AutoCalcFromClipboard = false;
            } else {
                ToggleClipListener = true;
                AutoCalcFromClipboard = true;
            }
        } else {
            SetStatus(NoAcces);
        }
    }

    private void ItemSaveSettingsActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        if (loggedIn) {
            if (ToggleOnTop) {
                prefs.put("_TRAVCALC_" + username + "_ONTOP_", "1");
            } else {
                prefs.put("_TRAVCALC_" + username + "_ONTOP_", "0");
            }
            if (ToggleSmallWindow) {
                prefs.put("_TRAVCALC_" + username + "_SMALLWINDOW_", "1");
            } else {
                prefs.put("_TRAVCALC_" + username + "_SMALLWINDOW_", "0");
            }
            if (ShowFullBattles) {
                prefs.put("_TRAVCALC_" + username + "_FULLBATTLES_", "1");
            } else {
                prefs.put("_TRAVCALC_" + username + "_FULLBATTLES_", "0");
            }
            if (ToggleClipListener) {
                prefs.put("_TRAVCALC_" + username + "_CLIPLISTENER_", "1");
            } else {
                prefs.put("_TRAVCALC_" + username + "_CLIPLISTENER_", "0");
            }
            if (ToggleResCalc) {
                prefs.put("_TRAVCALC_" + username + "_RESCALC_", "1");
            } else {
                prefs.put("_TRAVCALC_" + username + "_RESCALC_", "0");
            }
            if (ItemSoundToggle.isSelected()) {
                prefs.put("_TRAVCALC_" + username + "_SOUND_", "1");
            } else {
                prefs.put("_TRAVCALC_" + username + "_SOUND_", "0");
            }
            SetStatus("Settings saved!");
        } else {
            SetStatus(NoAcces);
        }
    }

    private void ResTotalFieldActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
    }

    private void btnCountResourcesActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        DoCountRes();
    }

    private void DoCountRes() {

        if (loggedIn) {
            /* calc de res! */
            String data = getClipboardContents();
            BufferedReader in = new BufferedReader(new StringReader(data));
            int[] res = ParseResources(in);
            in = new BufferedReader(new StringReader(data));
            int power = GemPower(in);

            if (BrimPrev != res[0] || CrysPrev != res[1] || EssPrev != res[2] || GranPrev != res[3]) {

                /* set current values to prev */
                BrimPrev = res[0];
                CrysPrev = res[1];
                EssPrev = res[2];
                GranPrev = res[3];

                Brim += res[0];
                Crys += res[1];
                Ess += res[2];
                Gran += res[3];
                Integer totaal = res[0] + res[1] + res[2] + res[3];
                Total += totaal;

                String BrimStr = Brim.toString();
                String CrysStr = Crys.toString();
                String EssStr = Ess.toString();
                String GranStr = Gran.toString();

                DecimalFormat decformat = new DecimalFormat("0.0");

                if (Brim.intValue() > 5000) {
                    double tmpBrim = Brim.doubleValue() / 1000;
                    //Integer tmpBrim = Brim.intValue()/1000;

                    BrimStr = decformat.format(tmpBrim) + "k";
                }
                if (Crys.intValue() > 5000) {
                    double tmpCrys = Crys.doubleValue() / 1000;
                    CrysStr = decformat.format(tmpCrys) + "k";
                }
                if (Ess.intValue() > 5000) {
                    double tmpEss = Ess.doubleValue() / 1000;
                    EssStr = decformat.format(tmpEss) + "k";
                }
                if (Gran.intValue() > 5000) {
                    double tmpGran = Gran.doubleValue() / 1000;
                    GranStr = decformat.format(tmpGran) + "k";
                }

                ResBrimField.setText(BrimStr);
                ResCrysField.setText(CrysStr);
                ResEssField.setText(EssStr);
                ResGranField.setText(GranStr);

                Integer totaaal = Total.intValue();
                String TotalStr = totaaal.toString();

                if (totaaal.intValue() > 5000) {
                    double tmpTotal = totaaal.doubleValue() / 1000;
                    TotalStr = decformat.format(tmpTotal) + "k";
                }

                ResTotalField.setText(TotalStr);
                if (totaal > 0) {
                    SetStatus("Resources increased with " + totaal.intValue() + " !");
                    txtStatus.setBackground(Color.orange);
                    if (power > 0) {
                        SetStatus(txtStatus.getText() + " Gem: " + power + " power");
                    }
                }



            } else {
                System.out.println("Res amount exact same as previous, therefore is skipped counting!");
            }


        } else {
            SetStatus(NoAcces);
        }


    }

    private void ResEssFieldActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
    }

    private void ResCrysFieldActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
    }

    private int[] ParseResources(BufferedReader in) {

        int[] res = new int[4];
        String str = "";
        int b, c, e, g = 0;


        try {
            while ((str = in.readLine()) != null) {

                str = str.trim();
                str = str.replaceAll(" ", "");
                str = str.replaceAll("\t", "  ");

                Pattern p0 = Pattern.compile("Yougained\\d{1,6}brimstone,\\d{1,6}crystal,\\d{1,6}essenceand\\d{1,6}granite.");
                Matcher m0 = p0.matcher(str);

                if (str.length() > 0 && m0.find()) {

                    /* brim selecteren */
                    Pattern p = Pattern.compile("\\d{1,9}brimstone");
                    Matcher m = p.matcher(str);
                    m.find();
                    b = Integer.parseInt(m.group().replaceAll("brimstone", ""));

                    /* crys selecteren */
                    p = Pattern.compile("\\d{1,9}crystal");
                    m = p.matcher(str);
                    m.find();
                    c = Integer.parseInt(m.group().replaceAll("crystal", ""));

                    /* brim selecteren */
                    p = Pattern.compile("\\d{1,9}essence");
                    m = p.matcher(str);
                    m.find();
                    e = Integer.parseInt(m.group().replaceAll("essence", ""));

                    /* brim selecteren */
                    p = Pattern.compile("\\d{1,9}granite");
                    m = p.matcher(str);
                    m.find();
                    g = Integer.parseInt(m.group().replaceAll("granite", ""));

                    /* controle of deze methode voldoet */
                    if ((b + c + e + g) > 0) {
                        res[0] = b;
                        res[1] = c;
                        res[2] = e;
                        res[3] = g;
                        break;
                    }
                }
            }
            in.close();

        } catch (IllegalStateException ex) {
            SetStatus("ERROR: Couldn't calc resources!)");
            theLogger.severe("ERROR: RESCALC ERROR: " + ex.toString());
        } catch (ArrayIndexOutOfBoundsException ex) {
            SetStatus("ERROR: Couldn't calc resources!)");
            theLogger.severe("ERROR: RESCALC ERROR: " + ex.toString());
        } catch (IOException ex) {
            SetStatus("ERROR: Couldn't calc resources!)");
            theLogger.severe("ERROR: RESCALC ERROR: " + ex.toString());
        }

        return res;
    }

    private int[] ParseTravelResources(BufferedReader in) {

        int[] res = new int[4];
        String str;
        int b = 0;
        int c = 0;
        int e = 0;
        int g = 0;


        try {

            while ((str = in.readLine()) != null) {

                str = str.trim();
                str = str.replaceAll(" ", "");
                str = str.replaceAll("\t", "");

                Pattern p0 = Pattern.compile("Brimstone:\\d{1,9}");
                Matcher m0 = p0.matcher(str);

                Pattern p1 = Pattern.compile("Crystal:\\d{1,9}");
                Matcher m1 = p1.matcher(str);

                Pattern p2 = Pattern.compile("Essence:\\d{1,9}");
                Matcher m2 = p2.matcher(str);

                Pattern p3 = Pattern.compile("Granite:\\d{1,9}");
                Matcher m3 = p3.matcher(str);

                if (m0.find()) {
                    /* brim selecteren */
                    b = Integer.parseInt(m0.group().replaceAll("Brimstone:", "").trim());
                }
                if (m1.find()) {
                    /* brim selecteren */
                    c = Integer.parseInt(m1.group().replaceAll("Crystal:", "").trim());
                }
                if (m2.find()) {
                    /* brim selecteren */
                    e = Integer.parseInt(m2.group().replaceAll("Essence:", "").trim());
                }
                if (m3.find()) {
                    /* brim selecteren */
                    g = Integer.parseInt(m3.group().replaceAll("Granite:", "").trim());
                }
                if (b > 0 && c > 0 && e > 0 && g > 0) {
                    break;
                }
            }
            res[0] = b;
            res[1] = c;
            res[2] = e;
            res[3] = g;

            in.close();

        } catch (IllegalStateException ex) {
            SetStatus("ERROR: Couldn't calc resources!)");
            theLogger.severe("ERROR: RESCALC ERROR: " + ex.toString());
        } catch (ArrayIndexOutOfBoundsException ex) {
            SetStatus("ERROR: Couldn't calc resources!)");
            theLogger.severe("ERROR: RESCALC ERROR: " + ex.toString());
        } catch (IOException ex) {
            SetStatus("ERROR: Couldn't calc resources!)");
            theLogger.severe("ERROR: RESCALC ERROR: " + ex.toString());
        }

        return res;
    }

    private void ClipboardDataChanged() {
        if (loggedIn) {
            if (AutoCalcFromClipboard) {

                AttackingString = getClipboardContents();
                if (!ToggleSmallWindow) {
                    txtInput.setText(AttackingString);
                }
                StartCalc(1);
            }

            System.out.println("gestart!");

        } else {
            SetStatus(NoAcces);
        }
    }

    private void FlipFullBattles() {
        if (ShowFullBattles) {
            ShowFullBattles = false;
        } else {
            ShowFullBattles = true;
        }
        StartCalc(1);
    }

    private void hideResCalc() {
        pnlOnder.setVisible(false);
        btnCountResources.setVisible(false);
    }

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {

    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {
    }

    private void frmLoginWindowLostFocus(java.awt.event.WindowEvent evt) {
        frmLogin.requestFocus();
        if (txtUserName.getText().equals("")) {
            txtUserName.requestFocus();
        } else {
            txtUserPassword.requestFocus();
        }
    }

    private void DoLogin() {

        if (checkLogin(username, userpass)) {

            try {
                btnLogin.setEnabled(false);
                String encusername = URLEncoder.encode(username, "UTF-8");
                String encpass = URLEncoder.encode(userpass, "UTF-8");
                URL sim = new URL("http://cq2.stormy.be/calcget/check.php?user=" + encusername + "&pass=" + encpass + "&action=listing");


                BufferedReader in = new BufferedReader(new FileReader("C:\\list.txt"));

                /* create travstat */
                try {
                    if (TravStatChoice && StatId == 0) {
                        URL urlstat = new URL("http://cq2.stormy.be/calcget/travstat.php?user=" + encusername + "&pass=" + encpass + "&action=startsession");


                        BufferedReader statread = new BufferedReader(new FileReader("C:\\list.txt"));
                        StatId = Integer.parseInt(statread.readLine().trim());
                    }

                    System.out.println(StatId);

                } catch (Exception e) {
                    /* program can continue: soft error! */
                    theLogger.warning("WARNING: LOGIN-STAT error: " + e.toString());
                }

                btnLogin.setEnabled(true);

                if (!ParseListRegExp(1, in)) {
                    /* probeer reguliere expressies */
                    SetStatus("Login succesfull.");
                    frmLogin.setEnabled(false);
                    frmLogin.setVisible(false);

                    loggedIn = true;

                    /* set username property */
                    prefs.put("_TRAVCALC_USER_NAME_", username);

                    /* get & perform visual properties */
                    String showontop = prefs.get("_TRAVCALC_" + username + "_ONTOP_", "");
                    String showsmallwindow = prefs.get("_TRAVCALC_" + username + "_SMALLWINDOW_", "");
                    String showfullbattles = prefs.get("_TRAVCALC_" + username + "_FULLBATTLES_", "");
                    String showcliplistener = prefs.get("_TRAVCALC_" + username + "_CLIPLISTENER_", "");
                    String showrescalc = prefs.get("_TRAVCALC_" + username + "_RESCALC_", "");
                    String strSound = prefs.get("_TRAVCALC_" + username + "_SOUND_", "");

                    if (showontop.equals("1")) {
                        ToggleOnTop = true;
                        this.setAlwaysOnTop(true);
                        ItemSetAlwaysOnTop.setState(true);
                    }
                    if (showsmallwindow.equals("1")) {
                        makeWindowSmall();
                    }
                    if (showfullbattles.equals("1")) {
                        ShowFullBattles = true;
                        ItemShowFullBattles.setState(true);
                    }
                    if (showcliplistener.equals("0")) {
                        ToggleClipListener = false;
                        AutoCalcFromClipboard = false;
                        ItemAutoCalcFromClipboard.setState(false);
                    }
                    if (strSound.equals("0")) {
                        ItemSoundToggle.setSelected(false);
                    }

                    if (showrescalc.equals("0")) {
                        HideResCalc();
                    }

                } else {
                    txtLoginStatus.setText("Cannot parse your list from database!");
                }

            } catch (IOException e) {
                txtLoginStatus.setText("ERROR: Wrong Input provided (copy/paste the entire encounter please!)");
                theLogger.severe("ERROR: LOGIN/LIST ERROR: " + e.toString());
            } catch (Exception e) {
                txtLoginStatus.setText("ERROR: General Login Error");
                theLogger.severe("ERROR: General Login Error: " + e.toString());
            }

        } else {
            txtLoginStatus.setText("Username/Password not correct!");
            theLogger.info("INFO: USERNAME/PASS NOT CORRECT: UserName='" + username + "' UserPass='***ENCRYPTED***'");
            txtUserPassword.requestFocus();
            txtUserPassword.selectAll();
        }
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {

        /* fetch user data */
        username = txtUserName.getText();
        userpass = getMD5Hash(new String(txtUserPassword.getPassword()));
        DoLogin();

    }

    private void btnBeforeResCalcActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        int[] res = ParseTravelResources(new BufferedReader(new StringReader(txtResInputBefore.getText())));
        lblBrimRes1.setText(Integer.toString(res[0]));
        lblCrysRes1.setText(Integer.toString(res[1]));
        lblEssRes1.setText(Integer.toString(res[2]));
        lblGranRes1.setText(Integer.toString(res[3]));

        ResCountUpTotal();

    }

    private void btnAfterResCalcActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int[] res = ParseTravelResources(new BufferedReader(new StringReader(txtResInputAfter.getText())));
        lblBrimRes2.setText(Integer.toString(res[0]));
        lblCrysRes2.setText(Integer.toString(res[1]));
        lblEssRes2.setText(Integer.toString(res[2]));
        lblGranRes2.setText(Integer.toString(res[3]));

        ResCountUpTotal();

    }

    private void ItemClearResourcesActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        BrimPrev = 0;
        CrysPrev = 0;
        EssPrev = 0;
        GranPrev = 0;

        Brim = 0;
        Crys = 0;
        Ess = 0;
        Gran = 0;
        Total = 0;

        Brim2 = 0;
        Crys2 = 0;
        Ess2 = 0;
        Gran2 = 0;
        Total2 = 0;

        ResBrimField.setText(Brim.toString());
        ResCrysField.setText(Crys.toString());
        ResEssField.setText(Ess.toString());
        ResGranField.setText(Gran.toString());
        ResTotalField.setText(Total.toString());

        SetStatus("Resource Calc Cleared");

    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtUserPasswordActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
    }

    private void ResCountUpTotal() {

        Brim2 = Integer.parseInt(lblBrimRes2.getText()) - Integer.parseInt(lblBrimRes1.getText());
        lblBrimRes3.setText(Brim2.toString());
        Crys2 = Integer.parseInt(lblCrysRes2.getText()) - Integer.parseInt(lblCrysRes1.getText());
        lblCrysRes3.setText(Crys2.toString());
        Ess2 = Integer.parseInt(lblEssRes2.getText()) - Integer.parseInt(lblEssRes1.getText());
        lblEssRes3.setText(Ess2.toString());
        Gran2 = Integer.parseInt(lblGranRes2.getText()) - Integer.parseInt(lblGranRes1.getText());
        lblGranRes3.setText(Gran2.toString());

        lblTotalRes3.setText(Integer.toString((Brim2.intValue() + Crys2.intValue() + Ess2.intValue() + Gran2.intValue())));

    }

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            txtOutput.setText("");
            txtInput.setText("");
            SetStatus("");
            txtStatus.setBackground(Color.white);
            DeadCrits = 0;
            DIPCrits = 0;
            CritAantal = 0;
        } else {
            SetStatus(NoAcces);
        }
    }

    private void PasteCalcButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            AttackingString = getClipboardContents();
            if (!ToggleSmallWindow) {
                txtInput.setText(AttackingString);
            }
            StartCalc(1);
        } else {
            SetStatus(NoAcces);
        }
    }

    private void CalcButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (loggedIn) {
            AttackingString = txtInput.getText();
            StartCalc(1);
        } else {
            SetStatus(NoAcces);
        }
    }

    private boolean checkLatestVersion() {
        return true;

    }

    private void StartCalc(int travOrBattle) {

        /* boel resetten */
        CritAantal = 0;
        DeadCrits = 0;
        DIPCrits = 0;
        BattleNum = 0;

        ownExtraIthHealth = 0;
        ownExtraIthDamage = 0;
        ownExtraIthDefences = 0;

        ExtraIthHealth = 0;
        ExtraIthDamage = 0;
        ExtraIthDefences = 0;

        /* alle eigen crits ongebruikt flaggen */
        for (int i = 0; i < OwnCritAantal; i++) {
            owncreatures[i].setUsed(false);
        }
        boolean parseerror = false;

        if (travOrBattle == 1) {
            /* travel */
            /* encounter lijst parsen */
            BufferedReader in = new BufferedReader(new StringReader(AttackingString));
            parseerror = ParseListRegExp(2, in);
        } else if (travOrBattle == 2) {
            /* battle */
            /* eigen lijst parsen */
            /*BufferedReader in1 = new BufferedReader(new StringReader(BattleInputFieldOwn.getText()));
            
            parseerror = ParseListRegExp(1,in1);
            
             */
            /* encounter lijst parsen */
            /*
            
            BufferedReader in2 = new BufferedReader(new StringReader(BattleInputFieldOpponent.getText()));
            
            parseerror = ParseListRegExp(2,in2);
            
             */
        }


        /* als er geen parse-error opgetreden is */
        if (!parseerror) {

            if (travOrBattle == 1) {
                /* clear outputveld */
                if (CritAantal > 0) {
                    SetStatus("");
                    txtStatus.setBackground(Color.white);
                    txtOutput.setText("");
                }
            } else if (travOrBattle == 2) {
                /*
                
                BattleCalcStatus.setText("");
                
                BattleCalcStatus.setBackground(Color.white);
                
                BattleCalcField.setText("");
                
                 **/
            }
            gevecht.setLength(0);

            if (OwnCritAantal == 0) {
                txtStatus.setBackground(Color.white);
                SetStatus("No! Looks like something went wrong with your list :|");
                theLogger.info("INFO: List Size is Zero!");
            } else if (CritAantal == 0) {

                txtStatus.setBackground(Color.white);
                SetStatus("Omigod, nobody is attacking you!");
                theLogger.info("INFO: Encounter List Size is Zero!");

            } else if (CritAantal > OwnCritAantal) {
                txtStatus.setBackground(Color.white);
                Date today = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("H:mm:ss");
                String datenewformat = formatter.format(today);
                SetStatus("You cannot defend all creatures! (last calc: " + datenewformat + ")");
                theLogger.info("INFO: Encounter Size > List Size: Cannot Defend This!");
            } else {
                /* indien wel genoeg, calc ze */
                int i = 0;
                int j = 0;
                int succes = 0;

                ithIsInListAndInRange(1);
                ithIsInListAndInRange(2);


                //System.out.println(ownExtraIthHealth);
                //System.out.println(ExtraIthHealth);

                /* probeer auto defend */
                while (i < CritAantal && i < OwnCritAantal & !parseerror) {
                    succes = doBattle(owncreatures[i], creatures[i]);
                    //nummers[i][0]=i;
                    //nummers[i][1]=succes;
                    owncreatures[i].setUsed(true);
                    creatures[i].setUsed(true);
                    i++;
                }
                /* controleer of alle
                
                for(int a=0;a<Math.min(CritAantal,OwnCritAantal);a++){
                
                gevecht+=a+" -> "+nummers[a][0]+"("+nummers[a][1]+"), ";
                
                }
                
                
                
                
                
                /* alle gevechten gedaan */
                txtOutput.setText(gevecht.toString());
                String status = "";

                Date today = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("H:mm:ss");
                String datenewformat = formatter.format(today);

                /* als er dood zijn */
                if (DeadCrits > 0) {
                    txtStatus.setBackground(Color.red);
                    status = (DeadCrits + DIPCrits) + " of your creatures got killed!";
                    AantalBadCalcs++;
                    if (ItemSoundToggle.isSelected()) {
                        playSound(2);
                    }
                } else if (DIPCrits > 0) {
                    txtStatus.setBackground(Color.red);
                    status = DIPCrits + " of your creatures got dipped!";
                    AantalBadCalcs++;
                    if (ItemSoundToggle.isSelected()) {
                        playSound(1);
                    }
                } else {
                    /* als er geen dood zijn */
                    txtStatus.setBackground(Color.green);
                    status = "Auto Defend possible!";
                    AantalGoodCalcs++;
                    if (ItemSoundToggle.isSelected()) {
                        playSound(0);
                    }
                }
                SetStatus(status + " (last calc: " + datenewformat + ")");


            }

        }

    }

    private void SetStatus(String strTxt) {

        txtStatus.setText(strTxt);

        /* start - admin version only */

//        try {
//            BufferedWriter out = new BufferedWriter(new FileWriter("c:\\trigger\\travelcalcoutput.txt"));
//            out.write(strTxt);
//            out.close();
//        } catch (Exception ex) {
//            // ignore the error
//        }
         
        /* end - admin version only */

    }

    private int doBattle(Crit crit1, Crit crit2) {

        BattleNum++;

        /* defense CRIT1 ophalen */
        double damage1 = crit1.getDamage() + ownExtraIthDamage;
        double health1 = crit1.getHealth() + ownExtraIthHealth;
        int def1 = 0;
        /* def ophalen */
        if (crit2.getType().equals("Nature")) {
            def1 = crit1.getForestDefence() + ownExtraIthDefences;
            //System.out.println("Crit2's class is 'forest'");
        } else if (crit2.getType().equals("Diabolic")) {
            def1 = crit1.getDeathDefence() + ownExtraIthDefences;
            //System.out.println("Crit2's class is 'death'");
        } else if (crit2.getType().equals("Mystic")) {
            def1 = crit1.getAirDefence() + ownExtraIthDefences;
            //System.out.println("Crit2's class is 'air'");
        } else if (crit2.getType().equals("Elemental")) {
            def1 = crit1.getEarthDefence() + ownExtraIthDefences;
            //System.out.println("Crit2's class is 'earth'");
        } else {
            theLogger.warning("This Creature(2) Type is Not Found='" + crit2.getType() + "'");
        }

        /* defense CRIT2 ophalen */
        double damage2 = crit2.getDamage() + ExtraIthDamage;
        double health2 = crit2.getHealth() + ExtraIthHealth;
        int def2 = 0;
        /* def ophalen */
        if (crit1.getType().equals("Nature")) {
            def2 = crit2.getForestDefence() + ExtraIthDefences;
            //System.out.println("Crit1's class is 'forest'");
        } else if (crit1.getType().equals("Diabolic")) {
            def2 = crit2.getDeathDefence() + ExtraIthDefences;
            //System.out.println("Crit1's class is 'death'");
        } else if (crit1.getType().equals("Mystic")) {
            def2 = crit2.getAirDefence() + ExtraIthDefences;
            //System.out.println("Crit1's class is 'air'");
        } else if (crit1.getType().equals("Elemental")) {
            def2 = crit2.getEarthDefence() + ExtraIthDefences;
            //System.out.println("Crit1's class is 'earth'");
        } else {
            theLogger.warning("This Creature(1) Type is Not Found='" + crit2.getType() + "'");
        }

        /* CRIT1: MASS CRITS */
        if (crit1.getItem().equals("Dust Cloud") || crit1.getItem().equals("Weak Link")) {
            int tel = countCritFamily(1, crit1.getFamily());
            damage1 += ((15*2) * (tel));
        } else if (crit1.getItem().equals("Ash Cloud") || crit1.getItem().equals("Abominable Link")) {
            int tel = countCritFamily(1, crit1.getFamily());
            damage1 += ((18*2) * (tel));
        } else if (crit1.getItem().equals("Sand Cloud") || crit1.getItem().equals("Frightning Link")) {
            int tel = countCritFamily(1, crit1.getFamily());
            damage1 += ((21*2) * (tel));
        } else if (crit1.getItem().equals("Heat Mist") || crit1.getItem().equals("Nightmarish Link")) {
            int tel = countCritFamily(1, crit1.getFamily());
            damage1 += ((24*2) * (tel));
        } else if (crit1.getItem().equals("Poison Mist") || crit1.getItem().equals("Horrifying Link")) {
            int tel = countCritFamily(1, crit1.getFamily());
            damage1 += ((27*2) * (tel));
        } else if (crit1.getItem().equals("Acid Mist") || crit1.getItem().equals("Terrifying Link")) {
            int tel = countCritFamily(1, crit1.getFamily());
            damage1 += ((30*2) * (tel));
        }else if(crit1.getItem().equals("Bronze Armour")|| crit1.getItem().equals("Mash")){
            int tel = countCritFamily(1, crit1.getFamily());
            health1 += ((30*2) * (tel));
        }else if(crit1.getItem().equals("Silver Armour")|| crit1.getItem().equals("Stomp")){
            int tel = countCritFamily(1, crit1.getFamily());
            health1 += ((36*2) * (tel));
        }else if(crit1.getItem().equals("Gold Armour")|| crit1.getItem().equals("Squash")){
            int tel = countCritFamily(1, crit1.getFamily());
            health1 += ((42*2) * (tel));
        }else if(crit1.getItem().equals("Platinum Armour")|| crit1.getItem().equals("Smash")){
            int tel = countCritFamily(1, crit1.getFamily());
            health1 += ((48*2) * (tel));
        }else if(crit1.getItem().equals("Titanium Armour")|| crit1.getItem().equals("Trample")){
            int tel = countCritFamily(1, crit1.getFamily());
            health1 += ((54*2) * (tel));
        }else if(crit1.getItem().equals("Diamond Armour")|| crit1.getItem().equals("Crush")){
            int tel = countCritFamily(1, crit1.getFamily());
            health1 += ((60*2) * (tel));
        }      
     

        /* toon welke crits het tegen elkaar opnemen */
        gevecht.append("<i>Battle&nbsp;" + BattleNum + "</i>&nbsp;&nbsp;&nbsp;<b>" + crit1.getName() + " " + ((int) crit1.getDamage()) + "/" + ((int) crit1.getHealth()) + " " + crit1.getItem() + "</b><br>");
        gevecht.append("- <i>vs</i> -&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>" + crit2.getName() + " " + ((int) crit2.getDamage()) + "/" + ((int) crit2.getHealth()) + " " + crit2.getItem() + "</b><br>");

        /* intialisatie */
        int schade1, schade2;
        int teller = 50;

        /* starten met het gevecht */
        while (health1 > 0 && health2 > 0 && teller > 0) {

            /* item influence during battle */
            /* crit1: golem */

            if(crit1.getItem().equals("Sacrificial Dagger") && teller == 50){
                damage1 += 200;
            }else if(crit1.getItem().equals("Land Slide")){
                damage2 -= damage2*0.04;
            }else if(crit1.getItem().equals("Flash Flood")){
                damage2 -= damage2*0.06;
            }else if(crit1.getItem().equals("Fire Storm")){
                damage2 -= damage2*0.08;
            }else if(crit1.getItem().equals("Typhoon")){
                damage2 -= damage2*0.10;
            }else if(crit1.getItem().equals("Sand Storm")){
                damage2 -= damage2*0.12;
            }else if(crit1.getItem().equals("Deep Freeze")){
                damage2 -= damage2*0.15;
            }else if(crit1.getItem().equals("Cry")){
                damage1 += 50;
            }else if(crit1.getItem().equals("Bark")){
                damage1 += 80;
            }else if(crit1.getItem().equals("Snarl")){
                damage1 += 110;
            }else if(crit1.getItem().equals("Growl")){
                damage1 += 140;
            }else if(crit1.getItem().equals("Bellow")){
                damage1 += 170;
            }else if(crit1.getItem().equals("Howl")){
                damage1 += crit1.getDamage()*0.25;
            }else if(crit1.getItem().equals("Mild Poison")){
                damage1 += crit2.getHealth()*0.10;
            }else if(crit1.getItem().equals("Strong Poison")){
                damage1 += crit2.getHealth()*0.15;
            }else if(crit1.getItem().equals("Deadly Poison")){
                damage1 += crit2.getHealth()*0.20;
            }else if(crit1.getItem().equals("Deadly Poison")){
                damage1 += crit2.getHealth()*0.20;
            }


            /* limit */
            if (damage1 < 0) {
                damage1 = 0;
            }
            if (damage2 < 0) {
                damage2 = 0;
            }
            if (def1 < 0) {
                def1 = 0;
            }
            if (def2 < 0) {
                def2 = 0;
            }


            /* bereken schade da 1 aan 2 doet */
            double sch1 = (150 - def2) * damage1 / 100;
            schade1 = (int) Math.round(sch1);


            /* bereken schade da 2 aan 1 doet */
            double sch2 = (150 - def1) * damage2 / 100;
            schade2 = (int) Math.round(sch2);



            if(crit1.getItem().equals("Soaring Wings")){
                schade2 -= schade2*0.10;
            }else if(crit1.getItem().equals("Cleaving Talons")){
                schade2 -= schade2*0.15;
            }else if(crit1.getItem().equals("Menacing Beak")){
                schade2 -= schade2*0.20;
            }else if(crit1.getItem().equals("Slicing Tail")){
                schade2 -= schade2*0.25;
            }else if(crit1.getItem().equals("Fierce Manes")){
                schade2 -= schade2*0.30;
            }else if(crit1.getItem().equals("Deafening Roar")){
                schade2 -= schade2*0.35;
            }
            
            /* laat crit 1 op crit2 slaan */
            health2 = health2 - schade1;

            /* laat crit2 op crit1 slaan */
            health1 = health1 - schade2;

            if(crit1.getItem().equals("Sapping Touch")){
                health1 += schade1*0.05;
            }else if(crit1.getItem().equals("Draining Touch")){
                health1 += schade1*0.15;
            }else if(crit1.getItem().equals("Vampiric Touch")){
                health1 += schade1*0.25;
            }else if(crit1.getItem().equals("Sanguine Touch")){
                health1 += schade1*0.35;
            }


            if (health1 < 0) {
                if(crit1.getItem().equals("Flame of Renewal")){
                    health1 = crit1.getHealth()*0.35;
                    damage1 = crit1.getDamage()*0.35;
                }else if(crit1.getItem().equals("Fire of Renewal")){
                    health1 = crit1.getHealth()*0.45;
                    damage1 = crit1.getDamage()*0.45;
                }else if(crit1.getItem().equals("Inferno of Renewal")){
                    health1 = crit1.getHealth()*0.55;
                    damage1 = crit1.getDamage()*0.55;
                }else{
                    health1 = 0;
                }
            }
            if (health2 < 0) {
                health2 = 0;
            }

            /* Gevecht naar scherm sturen */
            if (ShowFullBattles) {
                gevecht.append("Your " + crit1.getName() + " did " + ((int) schade1) + " damage -> opponent's " + crit2.getName() + " has " + ((int) health2) + " health left.<br>");
                gevecht.append("Opponent's " + crit2.getName() + " did " + ((int) schade2) + " damage -> your " + crit1.getName() + " has " + ((int) health1) + " health left.<br>");
            }

            if(crit1.getItem().equals("SaDa") || crit1.getItem().equals("Sacrificial Dagger") && teller == 50){
                damage1 -= 200;
            }

            teller--;

        }

        int succes = 0;
        if (health1 <= 0 && health2 > 0) {
            gevecht.append("<font color=\"red\">Your " + crit1.getName() + " got killed by your opponent's " + crit2.getName() + ".</font><br><br>");
            DeadCrits++;
            succes = 0;
        } else if (health1 != 0 && health2 <= 0) {
            gevecht.append("<font color=\"green\">Your " + crit1.getName() + " killed your opponent's " + crit2.getName() + ".</font><br><br>");
            succes = 2;
        } else if (health1 <= 0 && health2 <= 0) {
            gevecht.append("<font color=\"#CC6600\">Your " + crit1.getName() + " killed your opponent's " + crit2.getName() + " but died in the process.</font><br><br>");
            DIPCrits++;
            succes = 1;
        } else if (teller == 0) {
            health1 = 0;
            health2 = 0;
            gevecht.append("<font color=\"red\">Your " + crit1.getName() + " got exhausted and died.</font><br><br>");
            DeadCrits++;
            succes = 0;
        }

        return succes;
    }





    private int countCritFamily(int wie, String fam) {
        int tel = 0;
        if (wie == 1) {
            for (int i = 0; i < Math.min(CritAantal, OwnCritAantal); i++) {
                if (owncreatures[i].getFamily().equals(fam)) {
                    tel++;
                }
            }
        } else if (wie == 2) {
            for (int i = 0; i < Math.min(CritAantal, OwnCritAantal); i++) {
                if (creatures[i].getFamily().equals(fam)) {
                    tel++;
                }
            }
        } else if (wie == 3) {
            /* ENKEL VOOR FIENDS ! niet familie vgl, maar aantal crits! check namen! */
            for (int i = 0; i < Math.min(CritAantal, OwnCritAantal); i++) {
                if (owncreatures[i].getName().equals(fam)) {
                    tel++;
                }
            }
        } else if (wie == 4) {
            /* ENKEL VOOR FIENDS ! niet familie vgl, maar aantal crits! check namen! */
            for (int i = 0; i < Math.min(CritAantal, OwnCritAantal); i++) {
                if (creatures[i].getName().equals(fam)) {
                    tel++;
                }
            }
        }


        return tel;
    }

    public boolean checkLogin(String uname, String pass) {
        return true;

    }

    public boolean ParseListRegExp(int listNum, BufferedReader in) {
        String str = "";
        boolean error = false;
        String naam;
        try {
            while ((str = in.readLine()) != null) {

                Pattern patCheckEnd = Pattern.compile("Please copy");
                Matcher matCheckEnd = patCheckEnd.matcher(str);

                if(matCheckEnd.find()){
                    break;
                }

                Pattern p0 = Pattern.compile("N\\d{1,3}/D\\d{1,3}/M\\d{1,3}/E\\d{1,3}");
                Matcher m0 = p0.matcher(str);

                str = str.replaceAll("\t", "  ");
                str = str.replaceAll("&#37", "  ");
                str = str.replaceAll(";", "  ");
                //str = str.replaceAll(" \\(N\\)","");
                str = str.replaceAll(" \\(", "");
                str = str.replaceAll("\\)", "");
                str = str.replaceAll("sacrifice, ", "");
                str = str.replaceAll("sacrifice", "");
                str = str.replaceAll("kingdom defense", "");
                str = str.replaceAll("defense", "");
                str = str.replaceAll("curse", "");
                str = str.replaceAll("cast ", "");
                str = str.replaceAll("morph ", "");
                str = str.replaceAll("     ", "  ");
                str = str.trim();

                //System.out.println(str);

                if (str.length() > 0 && m0.find()) {

	                    /* splitsen op level */
                    Pattern p = Pattern.compile("L\\d{1,3}");
                    Matcher m = p.matcher(str);
                    m.find();
                    String level = m.group();
                    String[] kant = p.split(str);

	                    /* bewerking op eerste deel */
	                    /* klasse eruit halen */
                    p = Pattern.compile("Elemental|Diabolic|Mystic|Nature");
                    m = p.matcher(kant[0]);
                    m.find();
                    String klasse = m.group();
                    int klas = 0;
                    //System.out.println("klasse:"+klasse);

	                    /* familie eruit halen */
                    if (klasse.equals("Mystic")) {
                        klas = 3;
                        p = Pattern.compile("Djinn|Dragon|Pegasus|Griffin|Harpy|Phoenix|Gorgon|Manticore|Valkyrie|Mystic Familiar/");

                    } else if (klasse.equals("Diabolic")) {
                        klas = 2;
                        p = Pattern.compile("Cerberus|Shinigami|Cursed Being|Demon|Shade|Skeleton|Spectre|Wight|Wraith|Diabolic Familiar/");

                    } else if (klasse.equals("Elemental")) {
                        klas = 4;
                        p = Pattern.compile("Behemoth|Hrimthur|Tetramorph|Salamander|Sea Dweller|Titan|Nix|Siren|Wyrm|Elemental Familiar/");

                    } else if (klasse.equals("Nature")) {
                        klas = 1;
                        p = Pattern.compile("Centaur|Dryad|Pixie|Spider|Vulpes|Elf|Ogre|Troll|Nymph|Nature Familiar/");
                    }
                    //System.out.println("klas:"+klas);
                    m = p.matcher(kant[0]);
                    m.find();
                    String familie = m.group().replaceAll("/", "");
                    //System.out.println("fam:"+kant[0]);

                    p = Pattern.compile("/" + klasse);
                    String[] kantlinks = p.split(str);

                    System.out.println(kantlinks[0]);

                    p = Pattern.compile("  ");
                    String[] kantlinksa = p.split(str);

                    naam = kantlinksa[0].trim();
	                    /*
	                    
	                    p = Pattern.compile("\\d{1,50}");
	                    
	                    m = p.matcher(kantlinks[0]);
	                    
	                    m.find();
	                    
	                    String naam = m.group();
	                    
	                    naam = naam.replaceAll("\\.","").trim();
	                    
	                     */



                    //System.out.println(kantlinks[0]);

	                    /* bewerking op tweede deel */
	                    /* damage/health eruit halen */
                    p = Pattern.compile("\\d{1,5}/\\d{1,5}");
                    m = p.matcher(kant[1]);
                    m.find();
                    String[] damhea = m.group().split("/");
                    int damage = Integer.parseInt(damhea[0]);
                    int health = Integer.parseInt(damhea[1]);

	                    /* defences eruit halen */
                    p = Pattern.compile("N\\d{1,3}");
                    m = p.matcher(kant[1]);
                    m.find();
                    int fdef = Integer.parseInt(m.group().replaceAll("N", ""));

                    p = Pattern.compile("/D\\d{1,3}");
                    m = p.matcher(kant[1]);
                    m.find();
                    int ddef = Integer.parseInt(m.group().replaceAll("/D", ""));

                    p = Pattern.compile("/M\\d{1,3}");
                    m = p.matcher(kant[1]);
                    m.find();
                    int adef = Integer.parseInt(m.group().replaceAll("/M", ""));

                    p = Pattern.compile("/E\\d{1,3}");
                    m = p.matcher(kant[1]);
                    m.find();
                    int edef = Integer.parseInt(m.group().replaceAll("/E", ""));

                    p = Pattern.compile("N\\d{1,3}/D\\d{1,3}/M\\d{1,3}/E\\d{1,3}");

                    String[] rechts = p.split(kant[1]);

                    String item = "";
                    String enchant = "";
                    String soortenchant = "";
                    int IthEff = 0;
                    if (rechts.length > 1) {
	                        /* item gevonden */
                        if (!familie.equals("Tempest") && !familie.equals("Seraph") && !familie.equals("Rift Dancer") && !familie.equals("Apocalypse")) {
	                            /* als het niet over een ith gaat: zoek item + evt enchant */

                            p = Pattern.compile("\\*MaBe|\\*MaIm");
                            String[] rechtskant = p.split(rechts[1]);
                            item = rechtskant[0].trim();

                            if (rechtskant.length > 1) {
	                                /* enchant gevonden */
                                m = p.matcher(kant[1]);
                                m.find();

                                soortenchant = m.group().replaceAll("\\*", "");
                                enchant = rechtskant[1].trim();

                            }
                        } else {
	                            /* indien wel een ith */
                            p = Pattern.compile("\\+");
                            String[] rechtskant = p.split(rechts[1]);
                            item = rechtskant[0].trim();

                            if (rechtskant.length > 1) {
                                p = Pattern.compile("\\d{1,5}");
                                m = p.matcher(rechtskant[1]);
                                m.find();
                                IthEff = Integer.parseInt(m.group().trim());
                            }
                            //System.out.print("ITH("+IthEff+"): ");
                        }
                    }
	                    /*
	                    
	                    System.out.print(naam+" ("+familie+"/"+klasse+" "+level+") "+damage+"/"+health+" F"+fdef+"/D"+ddef+"/A"+adef+"/E"+edef+" "+item);
	                    
	                    if(!soortenchant.equals("")){
	                    
	                    System.out.print("* ("+soortenchant+" "+enchant+")");
	                    
	                    } else if(IthEff!=0){
	                    
	                    System.out.print(" (+"+IthEff+" health)");
	                    
	                    }
	                    
	                    System.out.println("");
	                    
	                     */

	                    /* voer nieuw creature in */
                    if (listNum == 1) {
	                        /* eigen critlist */
                        owncreatures[OwnCritAantal] = new Crit(naam, level, klasse, familie, damage, health, fdef, ddef, adef, edef, item, (soortenchant + enchant), IthEff);
                        OwnCritAantal++;
                    } else if (listNum == 2) {
	                        /* encounter list */
                        creatures[CritAantal] = new Crit(naam, level, klasse, familie, damage, health, fdef, ddef, adef, edef, item, (soortenchant + enchant), IthEff);
                        CritAantal++;
                    }

	                    /* controle of deze methode voldoet */
                    if (naam.equals("") || klasse.equals("") || familie.equals("") || (damage == 0 && health == 0) || (fdef == 0 && ddef == 0 && adef == 0 && edef == 0)) {
                        if (listNum == 1) {
                            SetStatus("ERROR: Your list in dB has wrong syntax! (copy/paste it again & restart this program!)");
                            theLogger.severe("ERROR: WRONG LIST SYNTAX(1): " + "CritName='" + naam + "' Class='" + klasse + "' Race='" + familie + "' Damage='" + damage + "' Health='" + health + "' FDef='" + fdef + "' DDef='" + ddef + "' ADef='" + adef + "' EDef='" + edef + "'");
                            theLogger.severe("ORIG LINE='" + str + "'");
                            OwnCritAantal = 0;
                        } else if (listNum == 2) {
                            SetStatus("ERROR: Wrong Input provided (copy/paste the entire encounter please!)");
                            theLogger.severe("ERROR: WRONG ENCOUNTER SYNTAX(2): " + "CritName='" + naam + "' Class='" + klasse + "' Race='" + familie + "' Damage='" + damage + "' Health='" + health + "' FDef='" + fdef + "' DDef='" + ddef + "' ADef='" + adef + "' EDef='" + edef + "'");
                            theLogger.severe("ORIG LINE='" + str + "'");
                            CritAantal = 0;
                        }

                        error = true;
                    }

                }
            }
            in.close();

        } catch (IllegalStateException e) {
            if (listNum == 1) {
                SetStatus("ERROR: Your list in dB has wrong syntax! (copy/paste it again & restart this program!)");
                theLogger.severe("ERROR: LIST SYNTAX(1): " + e.toString());
                theLogger.severe("ORIG LINE='" + str + "'");
                OwnCritAantal = 0;
            } else if (listNum == 2) {
                SetStatus("ERROR: Wrong Input provided (copy/paste the entire encounter please!)");
                theLogger.severe("ERROR: ENCOUNTER SYNTAX(2): " + e.toString());
                CritAantal = 0;
            }
            error = true;

        } catch (ArrayIndexOutOfBoundsException e) {

            if (listNum == 1) {
                SetStatus("ERROR: Your list in dB has wrong syntax! (copy/paste it again & restart this program!)");
                theLogger.severe("ERROR: LIST SYNTAX(1) OUT OF BOUNDS: " + e.toString());
                OwnCritAantal = 0;
            } else if (listNum == 2) {
                SetStatus("ERROR: Wrong Input provided (copy/paste the entire encounter please!)");
                theLogger.severe("ERROR: ENCOUNTER SYNTAX(2) OUT OF BOUNDS: " + e.toString());
                CritAantal = 0;
            }
            error = true;

        } catch (IOException e) {
            SetStatus("ERROR: Wrong Input provided (copy/paste the entire encounter please!)");
            theLogger.severe("ERROR: GENERAL SYNTAX ERROR: " + e.toString());
            error = true;
            if (listNum == 1) {
                OwnCritAantal = 0;
            } else if (listNum == 2) {
                CritAantal = 0;
            }
        }

        return error;
    }

    public boolean ithIsInListAndInRange(int listNum) {

        /* posities van de iths */
        int[] ithpos = WhereAreIth(listNum);

        if (listNum == 1) {
            /* eigen lijst */
            int i = 0;
            int num;
            while (ithpos[i] > -1) {
                /* ith gevonden */
                num = ithpos[i];

                /* als het zich in de defendlist bevindt */
                if (num < CritAantal) {
                    //System.out.println("ITH GEVONDEN: "+owncreatures[num].getName()+" & "+owncreatures[num].getIthEff());

                    if (owncreatures[num].getFamily().equals("Rift Dancer")) {
                        ownExtraIthHealth += owncreatures[num].getIthEff();
                    } else if (owncreatures[num].getFamily().equals("Seraph")) {
                        ownExtraIthDamage += owncreatures[num].getIthEff();
                    } else if (owncreatures[num].getFamily().equals("Tempest")) {
                        ownExtraIthDefences += owncreatures[num].getIthEff();
                    }
                }

                i++;
            }

        } else if (listNum == 2) {
            /* andere lijst */
            int i = 0;
            int num;
            while (ithpos[i] > -1) {
                /* ith gevonden */
                num = ithpos[i];

                //System.out.println("ENEMY ITH in range: "+ithpos[i]+creatures[num].getName()+" & "+creatures[num].getIthEff());

                if (creatures[num].getFamily().equals("Rift Dancer")) {
                    ExtraIthHealth += creatures[num].getIthEff();
                } else if (creatures[num].getFamily().equals("Seraph")) {
                    ExtraIthDamage += creatures[num].getIthEff();
                } else if (creatures[num].getFamily().equals("Tempest")) {
                    ExtraIthDefences += creatures[num].getIthEff();
                }


                i++;
            }

        }
        return true;
    }

    public int[] WhereAreIth(int listNum) {

        /* posities van de iths in lijst :-) */
        int[] ithpos = new int[5];
        ithpos[0] = -1;
        ithpos[1] = -1;
        ithpos[2] = -1;
        ithpos[3] = -1;
        ithpos[4] = -1;

        try {
            int tel = 0;
            if (listNum == 1) {
                for (int i = 0; i < OwnCritAantal; i++) {
                    if (owncreatures[i].getIthEff() > 0) {
                        ithpos[tel] = i;
                        tel++;
                    }
                }
            } else if (listNum == 2) {
                for (int i = 0; i < CritAantal; i++) {
                    if (creatures[i].getIthEff() > 0) {
                        ithpos[tel] = i;
                        tel++;
                    }
                }
            }
            /*

            System.out.print("\nITHPOS: ");

            for(int i=0;i<4;i++){

            System.out.print(ithpos[i]+", ");

            }

            System.out.println("");

             */
        } catch (IndexOutOfBoundsException e) {
            SetStatus("ERROR: More than 4 iths in list is impossible!");
            theLogger.severe("ERROR: TOO MANY ITHS: ArrayLength='" + ithpos.length + "' ERR:" + e.toString());
        }
        return ithpos;
    }

    public String getClipboardContents() {

        String result = "";

        clipboard = getToolkit().getSystemClipboard();
        //odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);

        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                //highly unlikely since we are using a standard DataFlavor
                //System.out.println(ex);
                theLogger.severe("ERROR: " + ex.toString());
            } catch (IOException ex) {
                //System.out.println(ex);
                theLogger.severe("ERROR: " + ex.toString());
            }
        }
        return result;
    }

    private int GemPower(BufferedReader in) {

        String str = "";
        double power = 0;


        try {
            while ((str = in.readLine()) != null) {

                str = str.trim();
                //str = str.replaceAll(" ","");
                str = str.replaceAll("\t", " ");

                Pattern p0 = Pattern.compile("You found a .* Gem");
                Matcher m0 = p0.matcher(str);

                if (str.length() > 0 && m0.find()) {

                    /* enkel gemstring */
                    String gemstring = m0.group();
                    Pattern p1 = Pattern.compile(" ");
                    String[] gemsplit = p1.split(str);

                    String quality = gemsplit[4].toLowerCase();
                    String gemname = gemsplit[7].toLowerCase();
                    int gemlevel = Integer.parseInt(gemsplit[6].toLowerCase());

                    if (gemname.startsWith("aner")) {
                        power = 30.0;
                    } else if (gemname.startsWith("aru")) {
                        power = 44.4;
                    } else if (gemname.startsWith("asih")) {
                        power = 33.6;
                    } else if (gemname.startsWith("chi")) {
                        power = 51.6;
                    } else if (gemname.startsWith("dun")) {
                        power = 64.0;
                    } else if (gemname.startsWith("eda")) {
                        power = 61.5;
                    } else if (gemname.startsWith("efis")) {
                        power = 28.0;
                    } else if (gemname.startsWith("efu")) {
                        power = 55.2;
                    } else if (gemname.startsWith("eno")) {
                        power = 42.0;
                    } else if (gemname.startsWith("faerh")) {
                        power = 72.0;
                    } else if (gemname.startsWith("fau")) {
                        power = 59.3;
                    } else if (gemname.startsWith("fen")) {
                        power = 57.1;
                    } else if (gemname.startsWith("fih")) {
                        power = 53.3;
                    } else if (gemname.startsWith("ime")) {
                        power = 106.6;
                    } else if (gemname.startsWith("inu")) {
                        power = 41.0;
                    } else if (gemname.startsWith("jih")) {
                        power = 84.2;
                    } else if (gemname.startsWith("kih")) {
                        power = 69.6;
                    } else if (gemname.startsWith("len")) {
                        power = 76.2;
                    } else if (gemname.startsWith("lua")) {
                        power = 80.0;
                    } else if (gemname.startsWith("mae")) {
                        power = 123.0;
                    } else if (gemname.startsWith("muena")) {
                        power = 100.8;
                    } else if (gemname.startsWith("mul")) {
                        power = 114.3;
                    } else if (gemname.startsWith("nax")) {
                        power = 533.3;
                    } else if (gemname.startsWith("nefar")) {
                        power = 45.6;
                    } else if (gemname.startsWith("ner")) {
                        power = 47.1;
                    } else if (gemname.startsWith("nis")) {
                        power = 40.0;
                    } else if (gemname.startsWith("nuy")) {
                        power = 200.0;
                    } else if (gemname.startsWith("qaera")) {
                        power = 252.0;
                    } else if (gemname.startsWith("qah")) {
                        power = 145.5;
                    } else if (gemname.startsWith("qer")) {
                        power = 133.0;
                    } else if (gemname.startsWith("qulah")) {
                        power = 38.8;
                    } else if (gemname.startsWith("ruh")) {
                        power = 45.7;
                    } else if (gemname.startsWith("sah")) {
                        power = 48.6;
                    } else if (gemname.startsWith("tur")) {
                        power = 43.2;
                    } else if (gemname.startsWith("uax")) {
                        power = 533.3;
                    } else if (gemname.startsWith("uka")) {
                        power = 66.7;
                    } else if (gemname.startsWith("urtae")) {
                        power = 168.0;
                    } else if (gemname.startsWith("uxi")) {
                        power = 533.3;
                    } else if (gemname.startsWith("uyi")) {
                        power = 228.6;
                    } else if (gemname.startsWith("uza")) {
                        power = 94.2;
                    } else if (gemname.startsWith("vae")) {
                        power = 72.7;
                    } else if (gemname.startsWith("var")) {
                        power = 50.0;
                    } else if (gemname.startsWith("xil")) {
                        power = 266.7;
                    } else if (gemname.startsWith("xio")) {
                        power = 533.3;
                    } else if (gemname.startsWith("xis")) {
                        power = 533.3;
                    } else if (gemname.startsWith("yar")) {
                        power = 160.0;
                    } else if (gemname.startsWith("ycom")) {
                        power = 1337.0;
                    } else if (gemname.startsWith("yun")) {
                        power = 177.8;
                    } else if (gemname.startsWith("zaneh")) {
                        power = 56.0;
                    } else if (gemname.startsWith("zar")) {
                        power = 100.0;
                    } else if (gemname.startsWith("zey")) {
                        power = 88.9;
                    }


                    if (quality.startsWith("beautiful")) {
                        power = power * 1.2;
                    } else if (quality.startsWith("magnificent")) {
                        power = power * 1.4;
                    } else if (quality.startsWith("exquisite")) {
                        power = power * 1.6;
                    }

                    power = Math.round(power * gemlevel);

                }
            }
        } catch (Exception e) {
            theLogger.warning("Gem found, but not correct syntax: " + e.toString());
        }

        return (int) power;
    }

    public void playSound(int sound) {
        String soundUrl = failureSound;
        if (sound == 0) {
            soundUrl = okSound;
        } else if (sound == 1) {
            soundUrl = warningSound;
        } else if (sound == 2) {
            soundUrl = failureSound;
        }
        try {
            java.io.InputStream in = getClass().getResourceAsStream(soundUrl);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException e) {
            System.out.println("Sound files missing?");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**

     * @param args the command line arguments

     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Interface();
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JLabel BrimLabel;
    private javax.swing.JPanel BrimPanel2;
    private javax.swing.JPanel BrimPanel3;
    private javax.swing.JPanel BrimPanel5;
    private javax.swing.JButton CalcButton;
    private javax.swing.JButton ClearButton;
    private javax.swing.JLabel CrysLabel;
    private javax.swing.JPanel CrysPanel2;
    private javax.swing.JPanel CrysPanel3;
    private javax.swing.JPanel CrysPanel5;
    private javax.swing.JLabel EssLabel;
    private javax.swing.JPanel EssPanel2;
    private javax.swing.JPanel EssPanel3;
    private javax.swing.JPanel EssPanel5;
    private javax.swing.JLabel GranLabel;
    private javax.swing.JPanel GranPanel2;
    private javax.swing.JPanel GranPanel3;
    private javax.swing.JPanel GranPanel4;
    private javax.swing.JPanel GranPanel5;
    private javax.swing.JCheckBoxMenuItem ItemAutoCalcFromClipboard;
    private javax.swing.JMenuItem ItemClearResources;
    private javax.swing.JMenuItem ItemMoreInfo;
    private javax.swing.JMenuItem ItemSaveSettings;
    private javax.swing.JMenuItem ItemSeeCritList;
    private javax.swing.JCheckBoxMenuItem ItemSetAlwaysOnTop;
    private javax.swing.JMenuItem ItemShowBug;
    private javax.swing.JCheckBoxMenuItem ItemShowFullBattles;
    private javax.swing.JMenuItem ItemShowRes;
    private javax.swing.JCheckBoxMenuItem ItemShowResCalc;
    private javax.swing.JCheckBoxMenuItem ItemSoundToggle;
    private javax.swing.JCheckBoxMenuItem ItemToggleSmallWindow;
    private javax.swing.JMenu MainMenu;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu OptionsMenu;
    private javax.swing.JButton PasteCalcButton;
    private javax.swing.JTextField ResBrimField;
    private javax.swing.JTextField ResCrysField;
    private javax.swing.JTextField ResEssField;
    private javax.swing.JTextField ResGranField;
    private javax.swing.JTextField ResTotalField;
    private javax.swing.JLabel TotalLabel;
    private javax.swing.JButton btnAfterResCalc;
    private javax.swing.JButton btnBeforeResCalc;
    private javax.swing.JButton btnCountResources;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnResyncList;
    private javax.swing.JEditorPane critlistField;
    private javax.swing.JDialog frmCreatureListUpdate;
    private javax.swing.JDialog frmLogin;
    private javax.swing.JDialog frmMoreInfo;
    private javax.swing.JDialog frmNews;
    private javax.swing.JScrollPane frmNewsPanel;
    private javax.swing.JDialog frmResCalc;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblBrimRes1;
    private javax.swing.JLabel lblBrimRes2;
    private javax.swing.JLabel lblBrimRes3;
    private javax.swing.JLabel lblCrysRes1;
    private javax.swing.JLabel lblCrysRes2;
    private javax.swing.JLabel lblCrysRes3;
    private javax.swing.JLabel lblEssRes1;
    private javax.swing.JLabel lblEssRes2;
    private javax.swing.JLabel lblEssRes3;
    private javax.swing.JLabel lblGranRes1;
    private javax.swing.JLabel lblGranRes2;
    private javax.swing.JLabel lblGranRes3;
    private javax.swing.JLabel lblHeaderBefore;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblLabelBrim2;
    private javax.swing.JLabel lblLabelBrim3;
    private javax.swing.JLabel lblLabelBrim5;
    private javax.swing.JLabel lblLabelCrys2;
    private javax.swing.JLabel lblLabelCrys3;
    private javax.swing.JLabel lblLabelCrys5;
    private javax.swing.JLabel lblLabelEss2;
    private javax.swing.JLabel lblLabelEss3;
    private javax.swing.JLabel lblLabelEss5;
    private javax.swing.JLabel lblLabelGran2;
    private javax.swing.JLabel lblLabelGran3;
    private javax.swing.JLabel lblLabelGran4;
    private javax.swing.JLabel lblLabelGran5;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTotalRes3;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblWelcome2;
    private javax.swing.JLabel lblWelcome3;
    private javax.swing.JLabel lblWelcome4;
    private javax.swing.JPanel pnlBoven;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JScrollPane pnlCreatureList;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlMidden;
    private javax.swing.JPanel pnlOnder;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JPanel pnlResCalc;
    private javax.swing.JPanel pnlResources;
    private javax.swing.JScrollPane sbInput;
    private javax.swing.JScrollPane sbOutput;
    private javax.swing.JEditorPane txtHTMLField;
    private javax.swing.JTextArea txtInput;
    private javax.swing.JTextField txtLoginStatus;
    private javax.swing.JEditorPane txtOutput;
    private javax.swing.JTextArea txtResInputAfter;
    private javax.swing.JTextArea txtResInputBefore;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JPasswordField txtUserPassword;
    // End of variables declaration                   
}
