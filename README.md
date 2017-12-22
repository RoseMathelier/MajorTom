Documentation utilisateur de l'API de géoréférencement vecteur MajorTom.

1) Installez la librairie (.jar) dans les dépendances.

2) Initialisez le contexte de transformation.

Context c = new Context(inputPath, outputPath, outputName);

Inutile de préciser ".shp" à la fin de outputName.

Si vous n'avez pas de shapefiles à géoréférencer mais que vous voulez tout de même tester cette magnifique API, vous pouvez utiliser les miens :

//Our basic coordinates
Coordinate c1 = new Coordinate(0,0,0);
Coordinate c2 = new Coordinate(0,1,0);
Coordinate c3 = new Coordinate(1,0,0);
Coordinate c4 = new Coordinate(1,1,0);

//Write the test point shapefile in the input directory.
Coordinate[] pc1 = new Coordinate[] {c1};
Coordinate[] pc2 = new Coordinate[] {c2};
Coordinate[] pc3 = new Coordinate[] {c3};
Coordinate[] pc4 = new Coordinate[] {c4};
List<Coordinate[]> pcoords = new ArrayList<Coordinate[]>();
Collections.addAll(pcoords, pc1, pc2, pc3, pc4);
ShapefileWriter pointWriter = new ShapefilePointWriter();
pointWriter.writeShp(pcoords, inputPath, "testPoint");

//Write the test line shapefile in the input directory.
Coordinate[] lc1 = new Coordinate[] {c1, c2};
Coordinate[] lc2 = new Coordinate[] {c3, c4};
List<Coordinate[]> lcoords = new ArrayList<Coordinate[]>();
Collections.addAll(lcoords,  lc1, lc2);
ShapefileWriter lineWriter = new ShapefileLineWriter();
lineWriter.writeShp(lcoords, inputPath, "testLine");

//Write the test polygon shapefile in the input directory.
Coordinate[] pgc = new Coordinate[] {c1, c2, c4, c3, c1};
List<Coordinate[]> pgcoords = new ArrayList<Coordinate[]>();
pgcoords.add(pgc);
ShapefileWriter polygonWriter = new ShapefilePolygonWriter();
polygonWriter.writeShp(pgcoords, inputPath, "testPolygon");

Attention à bien remplacer inputPath par le chemin de votre dossier d'entrée.

3) Rentrez des points d'appuis (ground control points).

GeometryFactory geomFactory = JTSFactoryFinder.getGeometryFactory();
Coordinate[] GCPBCoord1 = new Coordinate[] {new Coordinate(0,0,0)};
Coordinate[] GCPGCoord1 = new Coordinate[] {new Coordinate(2,1,0)};
CoordinateSequence GCPBasicCoord1 = new CoordinateArraySequence(GCPBCoord1);
CoordinateSequence GCPGroundCoord1 = new CoordinateArraySequence(GCPGCoord1);
ControlPoint GCP1 = new ControlPoint(GCPBasicCoord1,GCPGroundCoord1, geomFactory);
c.addGCP(GCP1);

4) Si vous le souhaitez, rentrez des points de contrôle (check points).

Coordinate[] CPBCoord1 = new Coordinate[] {new Coordinate(0,0,0)};
Coordinate[] CPGCoord1 = new Coordinate[] {new Coordinate(2,1,0)};
CoordinateSequence CPBasicCoord1 = new CoordinateArraySequence(CPBCoord1);
CoordinateSequence CPGroundCoord1 = new CoordinateArraySequence(CPGCoord1);
ControlPoint CP1 = new ControlPoint(CPBasicCoord1,CPGroundCoord1, geomFactory);
c.addGCP(CP1);

5) Initialisez le géoréférenceur de votre choix.

Georeferencer g = new PointGeoreferencer();
OU
Georeferencer g = new LineGeoreferencer();
OU
Georeferencer g = new PolygonGeoreferencer();

6) Ajoutez le contexte au géoréférencer.

g.setContext(c);

7) Lancez la transformation.

try {
	g.applyTransfo(myType);
} catch (NotEnoughGCPsException e) {
	e.printStackTrace();
}

La paramètre myType correspond au type de transformation : les deux possibilités (pour l'instant) sont TypeTransfo.LINEAIRE ou TypeTransfo.HELMERT.

8) Récupérez le résultat de votre transformation et visualisez le sur le SIG de votre choix.

9) Récupérez le rapport de votre transformation et visualisez le sur l'éditeur de texte de votre choix.

... OU SINON : si vous souhaitez travailler directement avec le code source (car pourquoi pas), vous pouvez aller dans le main où tout est expliqué et ou tout est prêt pour tester l'application.
