import { Component } from '@angular/core';
import { ConnectorService } from 'src/app/connector.service';
import { Countries } from './countries';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  countries: Countries[] = [];
  selectedCountry!: any;
  filteredCountry!: any;
  selectedUsers!: any;

  title = 'demicon-frontend';

  constructor(private connectorService: ConnectorService) {
    this.connectorService
      .getAll()
      .subscribe((data: Countries[]) => {
        this.countries = data;
      })
      .add(() => {
        this.selectedCountry = this.countries[0].name;
        this.selectedUsers = this.countries[0].users;
      });
  }

  onChange($event: any) {
    this.selectedCountry = $event.target.value;
    this.filteredCountry = this.countries.find(
      (country) => country.name === this.selectedCountry
    );
    this.selectedUsers = this.filteredCountry.users;
  }
}
