
  Pod::Spec.new do |s|
    s.name = 'CapacitorAppRateGfc'
    s.version = '0.0.1'
    s.summary = 'Plugin de notation d'une application ionic capacitor. Compatible avec Android X'
    s.license = 'MIT'
    s.homepage = 'https://github.com/SASGeniusFlashConception/capacitor-app-rate-gfc.git'
    s.author = 'SAS GeniusFlash Conception'
    s.source = { :git => 'https://github.com/SASGeniusFlashConception/capacitor-app-rate-gfc.git', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end