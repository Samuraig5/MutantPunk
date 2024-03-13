package Main.RenderLogic.Logo;

import Main.RenderLogic.Console;
import Main.Settings;

public class LogoScreen
{
    Console c;
    int xSize = Math.round(Settings.windowWidth/Settings.fontWidth);
    int ySize = Math.round(Settings.windowHeight/Settings.fontHeight);
    LogoCell[][] screen;

    public LogoScreen(Console c)
    {
        this.c = c;
    }

    public void initializeScreen()
    {
        screen = new LogoCell[xSize][ySize];

        char[][] mat = getLogoSources();

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                screen[x][y] = new LogoCell(' ');
            }
        }

        int xOff = Math.round(Settings.windowWidth/Settings.fontWidth/2)-Math.round(mat[0].length/2f);
        int yOff = 10;
        for (int y = 0; y < mat.length; y++) {
            for (int x = 0; x < mat[y].length; x++) {
                screen[x+xOff][y+yOff] = new LogoCell(mat[y][x]);
            }
        }
    }

    public char[][] getLogoScreen()
    {
        if (screen == null) {initializeScreen();}

        char[][] c = new char[screen.length][screen[0].length];

        for (int x = 0; x < c.length; x++) {
            for (int y = 0; y < c[x].length; y++) {
                c[x][y] = screen[x][y].getSymbol();
            }
        }
        return c;
    }

    public void updateLogoScreen()
    {

    }

    public String[] getStaticLogo()
    {
        String[] logo = new String[11];

        logo[0] =(" ███▄ ▄███▓ █    ██ ▄▄▄█████▓ ▄▄▄       ███▄    █ ▄▄▄█████▓    ██▓███   █    ██  ███▄    █  ██ ▄█▀\n");
        logo[1] =("▓██▒▀█▀ ██▒ ██  ▓██▒▓  ██▒ ▓▒▒████▄     ██ ▀█   █ ▓  ██▒ ▓▒   ▓██░  ██▒ ██  ▓██▒ ██ ▀█   █  ██▄█▒ \n");
        logo[2] =("▓██    ▓██░▓██  ▒██░▒ ▓██░ ▒░▒██  ▀█▄  ▓██  ▀█ ██▒▒ ▓██░ ▒░   ▓██░ ██▓▒▓██  ▒██░▓██  ▀█ ██▒▓███▄░ \n");
        logo[3] =("▒██    ▒██ ▓▓█  ░██░░ ▓██▓ ░ ░██▄▄▄▄██ ▓██▒  ▐▌██▒░ ▓██▓ ░    ▒██▄█▓▒ ▒▓▓█  ░██░▓██▒  ▐▌██▒▓██ █▄ \n");
        logo[4] =("▒██▒   ░██▒▒▒█████▓   ▒██▒ ░  ▓█   ▓██▒▒██░   ▓██░  ▒██▒ ░    ▒██▒ ░  ░▒▒█████▓ ▒██░   ▓██░▒██▒ █▄\n");
        logo[5] =("░ ▒░   ░  ░░▒▓▒ ▒ ▒   ▒ ░░    ▒▒   ▓▒█░░ ▒░   ▒ ▒   ▒ ░░      ▒▓▒░ ░  ░░▒▓▒ ▒ ▒ ░ ▒░   ▒ ▒ ▒ ▒▒ ▓▒\n");
        logo[6] =("░  ░      ░░░▒░ ░ ░     ░      ▒   ▒▒ ░░ ░░   ░ ▒░    ░       ░▒ ░     ░░▒░ ░ ░ ░ ░░   ░ ▒░░ ░▒ ▒░\n");
        logo[7] =("░      ░    ░░░ ░ ░   ░        ░   ▒      ░   ░ ░   ░         ░░        ░░░ ░ ░    ░   ░ ░ ░ ░░ ░ \n");
        logo[8] =("       ░      ░                    ░  ░         ░                         ░              ░ ░  ░   \n");
        logo[9] =("                 ░                                 ░          ░                    ░              \n");
        logo[10] =("       ░                           ░                                     ░                 ░      \n");

        return logo;
    }

    public char[][] getLogoSources()
    {
        String[] logo = new String[6];

        logo[0] =(" ███▄ ▄███  █    ██ ▄▄▄█████  ▄▄▄       ███▄    █ ▄▄▄█████     ██████   █    ██  ███▄    █  ██ ▄█▀\n");
        logo[1] =(" ██ ▀█▀ ██  ██   ██    ██     ████▄     ██ ▀█   █    ██        ██   ██  ██   ██  ██ ▀█   █  ██▄█  \n");
        logo[2] =(" ██     ██  ██   ██    ██     ██  ▀█▄   ██  ▀█ ██    ██        ██  ██   ██   ██  ██  ▀█ ██  ███▄  \n");
        logo[3] =(" ██     ██   █   ██    ██     ██▄▄▄▄██  ██   ▐███    ██        ██▄█      █   ██  ██   ▐▌██  ██ █▄ \n");
        logo[4] =(" ██     ██   █████     ██      █    ██  ██     ██    ██        ██        █████   ██     ██  ██  █▄\n");
        logo[5] =("                                     █                                                            \n");

        char[][] mat = new char[logo.length][logo[0].length()];

        for (int i = 0; i < logo.length; i++) {
            mat[i] = logo[i].toCharArray();
        }

        return mat;
    }
}
