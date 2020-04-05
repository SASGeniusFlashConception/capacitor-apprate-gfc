import {PluginListenerHandle} from "@capacitor/core";
import {APP_PUBLISH_STATUT, APPRATE_INIT, APPRATE_OPTS, APPRATE_STATUT} from "./interface";

declare module "@capacitor/core" {
    interface PluginRegistry {
        AppRateGFC: AppRateGFCPlugin;
    }
}

export interface AppRateGFCPlugin {
    // Initialisation du plugin. Les paramètres sont obligatoires
    init(options: APPRATE_OPTS): Promise<APPRATE_INIT>;

    // Methode permettant de vérifier si l'application est disponible sur le GooglePlay / AppStore
    checkAppStatus(options: { appPackageId: string; }): Promise<APP_PUBLISH_STATUT>;

    // Event déclenché lorsque lutilisateur clique sur le bouton positif
    addListener(eventName: 'positifRateEvent', listenerFunc: (statut: APPRATE_STATUT) => void): PluginListenerHandle;

    // Event déclenché lorsque l'utilisateur clique sur le bouton neutre
    addListener(eventName: 'netralRateEvent', listenerFunc: (statut: APPRATE_STATUT) => void): PluginListenerHandle;

    // Event déclenché lorsque l'utilisateur clique sur le bouton negatif
    addListener(eventName: 'negativeRateEvent', listenerFunc: (statut: APPRATE_STATUT) => void): PluginListenerHandle;

    // Event déclenché lorque l'on verifie si l'application existe dans le store souhaité. Egalement déclenché lorsque l'utilisateur clqiue sur le btn positif et que l'application n'existe pas
    addListener(eventName: 'appIsPublishEvent', listenerFunc: (statut: APP_PUBLISH_STATUT) => void): PluginListenerHandle;

    // Event déclenché si l'utilisateur a deja repondu a la demande
    addListener(eventName: 'isAllReadyShow', listenerFunc: (statut: boolean) => void): PluginListenerHandle;
}









