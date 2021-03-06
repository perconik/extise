# TODO

*   replace "native" Extise HTTP server with REST API

*   detect it current Ruby is JRuby and accordingly implement an alternative for `Extise.open`

*   add `--log[=<level>]` option for all import and measure scripts to automatically and thread-safely log `inform`, `warn`, `abort` and exceptions via exit hook to `./log/SCRIPT_NAME/%Y%m%d-%H%M%S-%L.log`, also write script name with ARGV and time on first log file line, see batch measure script for inspiration

*   support parametrized metrics, parameters should be included in the unique key for expertise records 

*   implement JGibbLDA bindings, force `--option` instead of `-option` on command line

*   implement JGibbLDA based concept infering & add some metrics on concept level

*   ignore too long sessions, hence sessions do not have to be consecutive

*   switch to https://github.com/thuss/standalone-migrations or make new total lightweight solution based on standalone-migrations as a gem 

*   since elements are not linked with interactions via structure handles, other element types can be considered to import 

    - support top-level-types (aka units, as is now), all types in general, and methods
    - element type should be stored on DB level
    - elements must be packed like: pack_files -> file + path
    - blocks on import must be grouped by file + path too
    - overall if there is somewhere just file as a key then it should be reviewed

*   import_extise_tasks: maybe filter even by classification, component + product

*   import_extise_tasks: should filter out product stuff like: Build, ApiTools, ...?

*   import_extise_commits: speedup, like for each repository we process only commits bound by interactions
    
        commits: a,b,c,d
        a -- b -> get time -> no interactions from bugs_eclipse_org
        b -- c -> get time -> some interactions => process a and b

*    consider linking elements with interactions via structure handles (due to some reasons this idea was abandoned because it seemed impossible), sample structure handles:
    
        =org.eclipse.pde.ui/src&lt;org.eclipse.pde.internal.ui.correction.java{QuickFixProcessor.java[QuickFixProcessor~getCorrections~QIInvocationContext;~\[QIProblemLocation;
        =org.eclipse.pde.ui/src&lt;org.eclipse.pde.internal.ui.editor.site{CategorySection.java[CategorySection^BUTTON_IMPORT_ENVIRONMENT
        =project/source-directory&lt;package{file[type~method~parameter;~parameter
        =project/source-directory&lt;package{file[type^constant

*   see & add to readme:
  
    - http://jgibblda.sourceforge.net/
    - https://wiki.eclipse.org/Mylyn/Integrator_Reference#Interaction_events
    - https://www.bugzilla.org/docs/2.16/html/dbschema.html
    - http://www.ravenbrook.com/tool/bugzilla-schema/?action=single&version=3.4.2&view=View+schema
