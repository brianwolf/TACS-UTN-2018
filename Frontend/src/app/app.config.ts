import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AppConfig {

  private config;
  private environment;

  constructor(private http: HttpClient) { }

  public getConfig(key) {
    return this.config[key];
  }

  public getEnv(key) {
    return this.environment[key];
  }

  public load() {
    return new Promise((resolve, reject) => {
      this.http.get('./assets/configuration/environment.json').subscribe((environment: any) => {
        console.log('ENVIRONMENT: ' + environment.environment);
        this.environment = environment;

        this.http.get(`./assets/configuration/config.${environment.environment}.json`).subscribe((config: any) => {
          console.log('API: ' + config.API);
          this.config = config;
          resolve(true);
        });

      });
    });
  }

}
