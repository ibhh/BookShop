/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ibhh.BookShop;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Simon
 */
public class MetricsHandler implements Serializable {

    private BookShop plugin;
    private Metrics metrics;
    public static HashMap<MTLocation, String> Shop = new HashMap<MTLocation, String>();
    public static HashMap<MTLocation, String> AdminShop = new HashMap<MTLocation, String>();
    public static int Error = 0;
    public int BookShopSignBuy = 0;
    public int BookShopAdminSignBuy = 0;

    public MetricsHandler(BookShop pl) {
        plugin = pl;
    }

    public void onStart() {
        try {
            metrics = new Metrics(plugin);
        } catch (IOException ex) {
            plugin.Logger("There was an error while submitting statistics.", "Error");
        }
        initializeGraphs();
        startStatistics();
    }

    public void saveStatsFiles() {
        try {
            ObjectManager.save(Shop, plugin.getDataFolder() + File.separator + "metrics" + File.separator + "Shop.statistics");
            plugin.Logger("Shops stats file contains " + calculateShopQuantity() + " values!", "Debug");
        } catch (Exception e) {
            plugin.Logger("Cannot save Shop statistics!", "Error");
            if (plugin.config.debug) {
                e.printStackTrace();
            }
        }
        try {
            ObjectManager.save(Shop, plugin.getDataFolder() + File.separator + "metrics" + File.separator + "AdminShop.statistics");
            plugin.Logger("AdminShops stats file contains " + calculateShopQuantity() + " values!", "Debug");
        } catch (Exception e) {
            plugin.Logger("Cannot save AdminShop statistics!", "Error");
            if (plugin.config.debug) {
                e.printStackTrace();
            }
        }
    }

    public void loadStatsFiles() {
        try {
            Shop = ObjectManager.load(plugin.getDataFolder() + File.separator + "metrics" + File.separator + "Shop.statistics");
            plugin.Logger("Shops stats file contains " + calculateShopQuantity() + " values!", "Debug");
            plugin.Logger("Stats loaded!", "Debug");
        } catch (Exception e) {
            plugin.Logger("Cannot load Shop statistics!", "Error");
            if (plugin.config.debug) {
                e.printStackTrace();
            }
        }
        try {
            Shop = ObjectManager.load(plugin.getDataFolder() + File.separator + "metrics" + File.separator + "AdminShop.statistics");
            plugin.Logger("AdminShops stats file contains " + calculateShopQuantity() + " values!", "Debug");
            plugin.Logger("Stats loaded!", "Debug");
        } catch (Exception e) {
            plugin.Logger("Cannot load AdminShop statistics!", "Error");
            if (plugin.config.debug) {
                e.printStackTrace();
            }
        }
    }

    private void startStatistics() {
        try {
            metrics.start();
        } catch (Exception ex) {
            plugin.Logger("There was an error while submitting statistics.", "Error");
        }
    }

    private void initializeGraphs() {
        initializeOthers();
        initializeDependenciesGraph();
        initializeCommandGraph();
    }

    public void initializeOthers() {
        Metrics.Graph ShopCountGraph = metrics.createGraph("Shops");
        ShopCountGraph.addPlotter(new Metrics.Plotter("BookShopSigns") {

            @Override
            public int getValue() {
                return calculateShopQuantity();
            }
        });
        ShopCountGraph.addPlotter(new Metrics.Plotter("BookShopAdminSigns") {

            @Override
            public int getValue() {
                return calculateAdminShopQuantity();
            }
        });
        Metrics.Graph GMGraph = metrics.createGraph("DefaultGameMode");
        GMGraph.addPlotter(new Metrics.Plotter(plugin.getServer().getDefaultGameMode().name()) {

            @Override
            public int getValue() {
                return 1;
            }
        });
        Metrics.Graph errorgraph = metrics.createGraph("uncatchedErrors");
        errorgraph.addPlotter(new Metrics.Plotter(plugin.getServer().getDefaultGameMode().name()) {

            @Override
            public int getValue() {
                return Error;
            }
            @Override
            public void reset() {
                Error = 0;
            }
        });
    }

    private void initializeCommandGraph() {
        Metrics.Graph CMDUses = metrics.createGraph("ShopUses");
        CMDUses.addPlotter(new Metrics.Plotter("BookShopSignBuy") {

            @Override
            public int getValue() {
                return BookShopSignBuy;
            }

            @Override
            public void reset() {
                BookShopSignBuy = 0;
            }
        });
        CMDUses.addPlotter(new Metrics.Plotter("BookShopAdminSignBuy") {

            @Override
            public int getValue() {
                return BookShopAdminSignBuy;
            }

            @Override
            public void reset() {
                BookShopAdminSignBuy = 0;
            }
        });
    }

    public void initializeDependenciesGraph() {
        Metrics.Graph depGraph = metrics.createGraph("EconomyDependencies");
        String iConomyName = "None";
        if (plugin.MoneyHandler.iConomyversion() != 0) {
            if (plugin.MoneyHandler.iConomyversion() == 1) {
                iConomyName = "Register";
            } else if (plugin.MoneyHandler.iConomyversion() == 2) {
                iConomyName = "Vault";
            } else if (plugin.MoneyHandler.iConomyversion() == 5) {
                iConomyName = "iConomy5";
            } else if (plugin.MoneyHandler.iConomyversion() == 6) {
                iConomyName = "iConomy6";
            }
        }
        depGraph.addPlotter(new Metrics.Plotter(iConomyName) {

            @Override
            public int getValue() {
                return 1;
            }
        });
        Metrics.Graph Permgraph = metrics.createGraph("PermissionDependencies");
        String PermName = "None";
        if (plugin.PermissionsHandler.PermPlugin != 0) {
            if (plugin.PermissionsHandler.PermPlugin == 1) {
                PermName = "BukkitPermissions";
            } else if (plugin.PermissionsHandler.PermPlugin == 2) {
                PermName = "PermissionsEX";
            } else if (plugin.PermissionsHandler.PermPlugin == 3) {
                PermName = "GroupManager";
            } else if (plugin.PermissionsHandler.PermPlugin == 4) {
                PermName = "bPermissions";
            }
        }
        Permgraph.addPlotter(new Metrics.Plotter(PermName) {

            @Override
            public int getValue() {
                return 1;
            }
        });
    }

    public int calculateShopQuantity() {
        int a = 0;
        for (String i : Shop.values()) {
            a++;
        }
        return a;
    }
    public int calculateAdminShopQuantity() {
        int a = 0;
        for (String i : AdminShop.values()) {
            a++;
        }
        return a;
    }
}