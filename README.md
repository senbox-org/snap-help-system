
[![pipeline status](https://gitlab.com/senbox-org/snap-help-system/badges/master/pipeline.svg)](https://gitlab.com/senbox-org/snap-help-system/-/commits/master)
[![coverage report](https://gitlab.com/senbox-org/snap-help-system/badges/master/coverage.svg)](https://gitlab.com/senbox-org/snap-help-system/-/commits/master)
[![Quality Gate Status](https://sonarqube.snap-ci.ovh/api/project_badges/measure?project=eu.esa.snap.netbeans%3Asnap-help-system&metric=alert_status&token=sqb_0cdea5fe313ef7ad167be393fb828795ba6d373b)](https://sonarqube.snap-ci.ovh/dashboard?id=eu.esa.snap.netbeans%3Asnap-help-system)

# snap-help-system
The help system for SNAP based on NetBeans

This module is mainly a copy of the [org-netbeans-modules-javahelp](https://github.com/apache/netbeans/tree/11.3/platform/javahelp/src/org/netbeans/modules/javahelp).
The version 11.3 has been used as basis. This copy was necessary as the support for javahelp was disabled due to license incompatibilities.
Those license conflicts between Apache (NetBeans) and GPL (JavaHelp) seem to be solved and NetBeans might reactivate the help in the future (version >=18).
Till than we need this module, and we don't have the license issue as SNAP is GPL too.

### Overview of Changes
* Differences between the original and this module are mainly in package names.
  * org.netbeans.api.javahelp --> eu.esa.snap.netbeans.javahelp.api
  * org.netbeans.modules.javahelp --> eu.esa.snap.netbeans.javahelp
* The services in [META-INF/services](src%2Fmain%2Fresources%2FMETA-INF%2Fservices) have been added.
* The resources have been copied to te [resources](src%2Fmain%2Fresources) path
