import {PluginListenerHandle} from "@capacitor/core";

declare module "@capacitor/core" {
  interface PluginRegistry {
    AppRateGFC: AppRateGFCPlugin;
  }
}

export interface AppRateGFCPlugin {
  // Initialisation du plugin. Les paramètres sont obligatoires
  init(options: APPRATEOPTS): Promise<string>;

  // Methode permettant de vérifier si l'application est disponible sur le GooglePlay / AppStore
  checkAppStatus(options: {appPackageId:string;}): Promise<{statut: APPPUBLISHSTATUT}>;

  // Event déclenché lorsque lutilisateur clique sur le bouton positif
  addListener(eventName: 'positifRateEvent', listenerFunc: (statut: APPRATESTATUT)=> void): PluginListenerHandle;

  // Event déclenché lorsque l'utilisateur clique sur le bouton neutre
  addListener(eventName: 'netralRateEvent', listenerFunc: (statut: APPRATESTATUT)=> void): PluginListenerHandle;

  // Event déclenché lorsque l'utilisateur clique sur le bouton negatif
  addListener(eventName: 'negativeRateEvent', listenerFunc: (statut: APPRATESTATUT)=> void): PluginListenerHandle;

  // Event déclenché lorque l'on verifie si l'application existe dans le store souhaité. Egalement déclenché lorsque l'utilisateur clqiue sur le btn positif et que l'application n'existe pas
  addListener(eventName: 'appIsPublishEvent', listenerFunc: (statut: APPPUBLISHSTATUT)=> void): PluginListenerHandle;
}


export interface APPRATEOPTS {
  // Nom de l'application
  appName: string;
  // Id de l'application - com.exemple.appname
  appPackageId: string;
  // Theme de la popup
  theme: THEMEENUM
  // Titre de la popup d'évaluation - le texte %@% sera remplacé par le nom de l'application
  titleAppRate: string;
  // Message de la popup d'évaluation - le texte %@% sera remplacé par le nom de l'application
  messageAppRate: string;
  // Nombre d'execution de l'application avant ouverture de la popup d'évaluation
  untilPrompt: number;
  // Nombre de jour avant ouverture de la popup d'évaluation
  daysUntilPrompt: number;
  // Texte du bouton positif
  positifBtnText: string;
  // Texte du bouton neutre
  netralBtnText: string;
  // Texte du bouton négatif
  negatifBtnText: string;
}

export enum THEMEENUM {
  LIGHT = 'light',
  DARK = 'dark'
}

export enum APPPUBLISHSTATUT {
  IS_PUBLISH, IS_NOT_PUBLISH
}

export enum APPRATESTATUT {
  POSITIVE, NETRAL, NEGATIVE
}
