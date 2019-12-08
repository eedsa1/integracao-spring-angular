import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { ModuleService } from '../service/module.service';

@Component({
  selector: 'app-modules',
  templateUrl: './modules.component.html',
  styleUrls: ['./modules.component.css']
})
export class ModulesComponent implements OnInit {
  modules = [];
  name = 'in28minutes';

  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private moduleService: ModuleService) { }

  ngOnInit() {
    this.moduleService.retrieveAllModules(this.authenticationService.username).subscribe(modules => {
      this.modules = modules;
    });
  }

  retrieveModules() {
    this.moduleService.retrieveAllModules(this.authenticationService.username).subscribe(modules => {
      this.modules = modules;
    });
  }

  addModule() {
    this.router.navigate([`/module/-1`]);
  }

  updateModule(id) {
    this.router.navigate([`/module/${id}`]);
  }

  deleteModule(id) {
    this.moduleService.deleteModule(this.authenticationService.username, id).subscribe(() => {
      this.retrieveModules();
    });
  }

}
