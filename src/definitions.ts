declare module "@capacitor/core" {
  interface PluginRegistry {
    AppRateGFC: AppRateGFCPlugin;
  }
}

export interface AppRateGFCPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
}
