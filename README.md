
Se agrego un progama extra para poder graficar los resultados:
	utils/plot2d.py
su uso es frecuentado en la instrucción 'make test', que tiene como parametros:
	java PhysicsLab 0.01 20 0.01


Si solo se quiere compilar:
	make

Si se quiere utilizar sin argumentos (dado que tiene robustez y lo consulta dentro del programa):
	make run
o en su defecto:
	java PhisicLab

O por el defecto de la tarea:
	java PhysicsLab <delta_time[s]> <end_time[s]> <sampling_time[s]>