# SnackBarGFC - Android

> Le plugin **capacitor-snackbar-gfc** est une implementation native du composant SnackBars d'Android.
> Vous pouvez maintenant utiliser ce package comme plugin [Ionic Capacitor](https://capacitor.ionicframework.com) dans votre application.

## Soutenez nos developpements
> Votre don nous permettra de developper plus de plugin open source mais également a maintenir ceux déjà publiés pour garantir une compatibilité avec les future version d'android, d'ios et de capacitor.
>Merci de votre soutien ! 

❤[Je fais un don](paypal.me/GFCPAYPAL)❤

1. [Plateformes prise en charge](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#1-Plateformes-prise-en-charge) 
2. [Installation](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#2-Installation) 
3. [Screenshot](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#3-Screenshot) 
4. [Méthodes du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#4-Méthodes-du-plugin) 
5. [Interfaces du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#5-Interfaces-du-plugin) 
    5. [SnackbarOpts](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#SnackbarOpts) 
6. [Enums du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#6-Enums-du-plugin) 
    6. [DURATION_ENUM](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#DURATION_ENUM) 
    6. [POSITION_ENUM](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#POSITION_ENUM)
7. [Utilisation du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#7-Utilisation-du-plugin) 
    7. [Intégration du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#Intégration du plugin) 
    7. [Méthode show(options:SnackbarOpts)](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#Méthode-show(options:SnackbarOpts)) 
    7. [Méthode addListener('snackbarEvent')](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#Méthode-addListener('snackbarEvent')) 
8. [Demo](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#8-Demo) 
9. [Nos autres plugins](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc#9-Nos-autres-plugins)


## 1. Plateformes prise en charge
- [x] Android

## 2. Installation
### Récuperation du package sur npm

    npm install capacitor-snackbar-gfc --save

### Ajout du plugin dans votre MainActivity.java
> Le fichier MainActivity est disponible dans ``app/java/packageid/`` depusi android studio

```java
    // Autres imports...
    import com.gfc.snackbargfc.SnackBarGFC;
    
    public class MainActivity extends BridgeActivity {
      @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
    
          add(SnackBarGFC.class);  // Ajout du plugin SnackBarGFC 
    
        }});
      }
    }
```

## 3. Screenshot

## 4. Méthodes du plugin

| Methodes                                                                                | Android   |
|:----------------------------------------------------------------------------------------|:----------|
| show(option: SnackbarOpts): Promise<{}>                                                 | Disponible|
| addListener(eventName: 'snackbarEvent', listenerFunc: () => void): PluginListenerHandle | Disponible|

## 5. Interfaces du plugin
### SnackbarOpts
| Propriété      | Etat       | Type         | Defaut   | Commentaire                                                 |
|:---------------|:-----------|:-------------|:---------|:------------------------------------------------------------|
| text           | Obligatoire| string       | null     |                                                             |
| btnText        | Obligatoire| string       | null     |                                                             |
| duration       | Obligatoire| DURATION_ENUM| 'short'  |                                                             |
| positionText   | Optionel   | POSITION_ENUM| null     |                                                             |
| backgroundColor| Optionel   | string       | '#4A4747'| Les coleurs devront commencer par # et contenir 6 caractères|
| buttonTextColor| Optionel   | string       | '#E35A5A'| Les coleurs devront commencer par # et contenir 6 caractères|
| messageColor   | Optionel   | string       | '#FFFFFF'| Les coleurs devront commencer par # et contenir 6 caractères|

## 6. Enums du plugin
### DURATION_ENUM
| Propriété | Valeur      |
|:----------|:------------|
| SHORT     | 'short'     |
| LONG      | 'long'      |
| INDEFINITE| 'indefinite'|                                                           
 
### POSITION_ENUM
 | Propriété| Valeur   |
 |:---------|:---------|
 | DEFAULT  | 'default'|
 | CENTER   | 'center' |

## 7. Utilisation du plugin
### Intégration du plugin
```typescript
// Autres imports...
import{Plugins} from '@capacitor/core'
const {SnackBarGFC} = Plugins;
```
### Méthode show(options: SnackbarOpts)
```typescript
const opts: SnackbarOpts = {
    text: 'Votre message...',
    btnText: 'Texte du bouton',
    duration: DURATION_ENUM.SHOT,
    positionText: POSITION_ENUM.DEFAULT,
    backgroundColor: '#4A4747',
    buttonTextColor: '#E35A5A',
    messageColor: '#FFFFFF'
}

SnackBarGFC.show(opts);
```
### Méthode addListener('snackbarEvent')
> Cette évenement est déclenché lorsque l'utilisateur clique sur le bouton du composant
```typescript
    ngOnInit() {
        SnackBarGFC.addListener('snackbarEvent', () => {
            // votre code...
        });
    }
```

## 8. Demo du plugin

> Téléchargez notre [demo](). Elle contient deux exemples: une snackbar light et une snackbar dark.

## 9. Nos autres plugins

| Nom              | Package                          | Version | Plateformes |
|:-----------------|:---------------------------------|:--------|:------------|
|    AppRateGFC    | [capacitor-datetimepicket-gfc]() | 1.0.0   | Android     |
| DateTimePickerGFC| [capacitor-datetimepicket-gfc]() | 1.0.0   | Android     |
| CalendarEventGFC | [capacitor-datetimepicket-gfc]() | 1.0.0   | Android     |
